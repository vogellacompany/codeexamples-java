/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vogella.gae.java.c2dm.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;


/**
 */
@SuppressWarnings("serial")
public class C2DMessaging {
    private static final String UPDATE_CLIENT_AUTH = "Update-Client-Auth";

    public static final String C2DM_SEND_ENDPOINT =
        "https://android.clients.google.com/c2dm/send";

    private static final Logger log = Logger.getLogger(C2DMessaging.class.getName());

    public static final String PARAM_REGISTRATION_ID = "registration_id";

    public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";
    
    public static final String PARAM_COLLAPSE_KEY = "collapse_key";

    private static final String UTF8 = "UTF-8";
    
    /**
     * Jitter - random interval to wait before retry.
     */
    public static final int C2DM_MAX_JITTER_MSEC = 3000;

    static C2DMessaging singleton;

    final C2DMConfigLoader serverConfig;
    
    private C2DMessaging(C2DMConfigLoader serverConfig) {
        this.serverConfig = serverConfig;
    }
    
    public synchronized static C2DMessaging get(ServletContext servletContext) {
        if (singleton == null) {
            C2DMConfigLoader serverConfig = new C2DMConfigLoader(getPMF(servletContext));
            singleton = new C2DMessaging(serverConfig);
        }
        return singleton;
    }
    
    public synchronized static C2DMessaging get(PersistenceManagerFactory pmf) {
        if (singleton == null) {
            C2DMConfigLoader serverConfig = new C2DMConfigLoader(pmf);
            singleton = new C2DMessaging(serverConfig);
        }
        return singleton;
    }
    
    C2DMConfigLoader getServerConfig() {
        return serverConfig;
    }

    /**
     * Initialize PMF - we use a context attribute, so other servlets can
     * be share the same instance. This is similar with a shared static
     * field, but avoids dependencies.
     */
    public static PersistenceManagerFactory getPMF(ServletContext ctx) {
        PersistenceManagerFactory pmfFactory =
            (PersistenceManagerFactory) ctx.getAttribute(
                    PersistenceManagerFactory.class.getName());
        if (pmfFactory == null) {
            pmfFactory = JDOHelper
                .getPersistenceManagerFactory("transactions-optional");
            ctx.setAttribute(
                    PersistenceManagerFactory.class.getName(),
                    pmfFactory);
        }
        return pmfFactory;
}

    
    public boolean sendNoRetry(String registrationId,
            String collapse,
            Map<String, String[]> params,
            boolean delayWhileIdle)
        throws IOException {
        
        // Send a sync message to this Android device.
        StringBuilder postDataBuilder = new StringBuilder();
        postDataBuilder.append(PARAM_REGISTRATION_ID).
            append("=").append(registrationId);

        if (delayWhileIdle) {
            postDataBuilder.append("&")
                .append(PARAM_DELAY_WHILE_IDLE).append("=1");
        }
        postDataBuilder.append("&").append(PARAM_COLLAPSE_KEY).append("=").
            append(collapse);

        for (Object keyObj: params.keySet()) {
            String key = (String) keyObj;
            if (key.startsWith("data.")) {
                String[] values = (String[]) params.get(key);
                postDataBuilder.append("&").append(key).append("=").
                    append(URLEncoder.encode(values[0], UTF8));
            }
        }

        byte[] postData = postDataBuilder.toString().getBytes(UTF8);

        // Hit the dm URL.
        URL url = new URL(C2DM_SEND_ENDPOINT);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
        String authToken = serverConfig.getToken();
        conn.setRequestProperty("Authorization", "GoogleLogin auth=" + authToken);

        OutputStream out = conn.getOutputStream();
        out.write(postData);
        out.close();
        
        int responseCode = conn.getResponseCode();
        
        if (responseCode == HttpServletResponse.SC_UNAUTHORIZED ||
                responseCode == HttpServletResponse.SC_FORBIDDEN) {
            // The token is too old - return false to retry later, will fetch the token
            // from DB. This happens if the password is changed or token expires. Either admin
            // is updating the token, or Update-Client-Auth was received by another server,
            // and next retry will get the good one from database.
            log.warning("Unauthorized - need token");
            serverConfig.invalidateCachedToken();
            return false;
        }
        
        // Check for updated token header
        String updatedAuthToken = conn.getHeaderField(UPDATE_CLIENT_AUTH);
        if (updatedAuthToken != null && !authToken.equals(updatedAuthToken)) {
            log.info("Got updated auth token from C2DM servers: " +
                    updatedAuthToken);
            serverConfig.updateToken(updatedAuthToken);
        }
            
        String responseLine = new BufferedReader(new InputStreamReader(conn.getInputStream()))
            .readLine();
            
        // NOTE: You *MUST* use exponential backoff if you receive a 503 response code.
        // Since App Engine's task queue mechanism automatically does this for tasks that
        // return non-success error codes, this is not explicitly implemented here.
        // If we weren't using App Engine, we'd need to manually implement this.
        log.info("Got " + responseCode + " response from Google C2DM endpoint.");
        
        if (responseLine == null || responseLine.equals("")) {
            throw new IOException("Got empty response from Google C2DM endpoint.");
        }

        String[] responseParts = responseLine.split("=", 2);
        if (responseParts.length != 2) {
            log.warning("Invalid message from google: " +
                    responseCode + " " + responseLine);
            throw new IOException("Invalid response from Google " +
                    responseCode + " " + responseLine);
        }

        if (responseParts[0].equals("id")) {
            log.info("Successfully sent data message to device: " + responseLine);
            return true;
        }
        
        if (responseParts[0].equals("Error")) {
            String err = responseParts[1];
            log.warning("Got error response from Google C2DM endpoint: " + err);
            // No retry.
            // TODO(costin): show a nicer error to the user.
            throw new IOException("Server error: " + err);
        } else {
            // 500 or unparseable response - server error, needs to retry
            log.warning("Invalid response from google " + responseLine + " " +
                    responseCode);
            return false;
        }
    }

    /**
     * Helper method to send a message, with 2 parameters.
     *
     * Permanent errors will result in IOException.
     * Retriable errors will cause the message to be scheduled for retry.
     */
    public void sendWithRetry(String token, String collapseKey,
            String name1, String value1, String name2, String value2)
                throws IOException {

        Map<String, String[]> params = new HashMap<String, String[]>();
        params.put("data." + name1, new String[] {value1});
        params.put("data." + name2, new String[] {value2});
        
        boolean sentOk = sendNoRetry(token, collapseKey, params, true);
        if (!sentOk) {
            retry(token, collapseKey, params, true);
        }
    }

    public boolean sendNoRetry(String token, String collapseKey,
            String name1, String value1, String name2, String value2)
                throws IOException {

        Map<String, String[]> params = new HashMap<String, String[]>();
        params.put("data." + name1, new String[] {value1});
        params.put("data." + name2, new String[] {value2});
        
        try {
            return sendNoRetry(token, collapseKey, params, true);
        } catch (IOException ex) {
            return false;
        }
    }
    
    private void retry(String token, String collapseKey,
            Map<String, String[]> params, boolean delayWhileIdle) {
        Queue dmQueue = QueueFactory.getQueue("c2dm");
        try {
            TaskOptions url =
                TaskOptions.Builder.withUrl(C2DMRetryServlet.URI)
                .param(C2DMessaging.PARAM_REGISTRATION_ID, token)
                .param(C2DMessaging.PARAM_COLLAPSE_KEY, collapseKey);
            if (delayWhileIdle) {
                url.param(PARAM_DELAY_WHILE_IDLE, "1");
            }
            for (String key: params.keySet()) {
                String[] values = (String[]) params.get(key);
                url.param(key, URLEncoder.encode(values[0], UTF8));
            }
            
            // Task queue implements the exponential backoff
            long jitter = (int) Math.random() * C2DM_MAX_JITTER_MSEC;
            url.countdownMillis(jitter);
            
            TaskHandle add = dmQueue.add(url);
        } catch (UnsupportedEncodingException e) {
            // Ignore - UTF8 should be supported
            log.log(Level.SEVERE, "Unexpected error", e);
        }
        
    }
        
}
