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

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * A task that sends tickles to device clients. This will be invoked by
 * AppEngine cron to retry failed requests.
 *
 * You must configure war/WEB-INF/queue.xml and the web.xml entries.
 */
@SuppressWarnings("serial")
public class C2DMRetryServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(C2DMRetryServlet.class.getName());

    public static final String URI = "/tasks/c2dm";

    public static final String RETRY_COUNT = "X-AppEngine-TaskRetryCount";

    static int MAX_RETRY = 3;
        
    /**
     * Only admin can make this request.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String registrationId = req.getParameter(C2DMessaging.PARAM_REGISTRATION_ID);
        String retryCount = req.getHeader(RETRY_COUNT);
        if (retryCount != null) {
            int retryCnt = Integer.parseInt(retryCount);
            if (retryCnt > MAX_RETRY) {
                log.severe("Too many retries, drop message for :" + registrationId);
                resp.setStatus(200);
                return; // will not try again.
            }
        }
        
        Map<String, String[]> params = req.getParameterMap();
        String collapse = req.getParameter(C2DMessaging.PARAM_COLLAPSE_KEY);
        boolean delayWhenIdle =
            null != req.getParameter(C2DMessaging.PARAM_DELAY_WHILE_IDLE);

        try {
            // Send doesn't retry !!
            // We use the queue exponential backoff for retries.
            boolean sentOk = C2DMessaging.get(getServletContext())
              .sendNoRetry(registrationId, collapse, params, delayWhenIdle);
            log.info("Retry result " + sentOk + " " + registrationId);
            if (sentOk) {
                resp.setStatus(200);
                resp.getOutputStream().write("OK".getBytes());
            } else {
                resp.setStatus(500); // retry this task
            }
        } catch (IOException ex) {
            resp.setStatus(200);
            resp.getOutputStream().write(("Non-retriable error:" +
              ex.toString()).getBytes());            
        }
    }
}
