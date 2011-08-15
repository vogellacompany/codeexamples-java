/*
 * Copyright 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.aaalars;

import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * Utility methods for getting the base URL for client-server communication and
 * retrieving shared preferences.
 */
public class Util {

    /**
     * Tag for logging.
     */
    private static final String TAG = "Util";

    // Shared constants

    /**
     * Key for account name in shared preferences.
     */
    public static final String ACCOUNT_NAME = "accountName";

    /**
     * Key for auth cookie name in shared preferences.
     */
    public static final String AUTH_COOKIE = "authCookie";

    /**
     * Key for connection status in shared preferences.
     */
    public static final String CONNECTION_STATUS = "connectionStatus";

    /**
     * Value for {@link #CONNECTION_STATUS} key.
     */
    public static final String CONNECTED = "connected";

    /**
     * Value for {@link #CONNECTION_STATUS} key.
     */
    public static final String CONNECTING = "connecting";

    /**
     * Value for {@link #CONNECTION_STATUS} key.
     */
    public static final String DISCONNECTED = "disconnected";

    /**
     * Key for device registration id in shared preferences.
     */
    public static final String DEVICE_REGISTRATION_ID = "deviceRegistrationID";

    /*
     * URL suffix for the RequestFactory servlet.
     */
    public static final String RF_METHOD = "/gwtRequest";

    /**
     * An intent name for receiving registration/unregistration status.
     */
    public static final String UPDATE_UI_INTENT = getPackageName() + ".UPDATE_UI";

    // End shared constants

    /**
     * Key for shared preferences.
     */
    private static final String SHARED_PREFS = "aaalars".toUpperCase(Locale.ENGLISH) + "_PREFS";

    /**
     * Cache containing the base URL for a given context.
     */
    private static final Map<Context, String> URL_MAP = new HashMap<Context, String>();

    /**
     * Display a notification containing the given string.
     */
    public static void generateNotification(Context context, String message) {
        int icon = R.drawable.status_icon;
        long when = System.currentTimeMillis();

        Notification notification = new Notification(icon, message, when);
        notification.setLatestEventInfo(context, "C2DM Example", message,
                PendingIntent.getActivity(context, 0, null, PendingIntent.FLAG_CANCEL_CURRENT));
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        SharedPreferences settings = Util.getSharedPreferences(context);
        int notificatonID = settings.getInt("notificationID", 0);

        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(notificatonID, notification);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("notificationID", ++notificatonID % 32);
        editor.commit();
    }

    /**
     * Returns the (debug or production) URL associated with the registration
     * service.
     */
    public static String getBaseUrl(Context context) {
        String url = URL_MAP.get(context);
        if (url == null) {
            // if a debug_url raw resource exists, use its contents as the url
            url = getDebugUrl(context);
            // otherwise, use the production url
            if (url == null) {
                url = Setup.PROD_URL;
            }
            URL_MAP.put(context, url);
        }
        return url;
    }

    /**
     * Creates and returns an initialized {@link RequestFactory} of the given
     * type.
     */
    public static <T extends RequestFactory> T getRequestFactory(Context context,
            Class<T> factoryClass) {
        T requestFactory = RequestFactorySource.create(factoryClass);

        SharedPreferences prefs = getSharedPreferences(context);
        String authCookie = prefs.getString(Util.AUTH_COOKIE, null);

        String uriString = Util.getBaseUrl(context) + RF_METHOD;
        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            Log.w(TAG, "Bad URI: " + uriString, e);
            return null;
        }
        requestFactory.initialize(new SimpleEventBus(),
                new AndroidRequestTransport(uri, authCookie));

        return requestFactory;
    }

    /**
     * Helper method to get a SharedPreferences instance.
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS, 0);
    }

    /**
     * Returns true if we are running against a dev mode appengine instance.
     */
    public static boolean isDebug(Context context) {
        // Although this is a bit roundabout, it has the nice side effect
        // of caching the result.
        return !Setup.PROD_URL.equals(getBaseUrl(context));
    }

    /**
     * Returns a debug url, or null. To set the url, create a file
     * {@code assets/debugging_prefs.properties} with a line of the form
     * 'url=http:/<ip address>:<port>'. A numeric IP address may be required in
     * situations where the device or emulator will not be able to resolve the
     * hostname for the dev mode server.
     */
    private static String getDebugUrl(Context context) {
        BufferedReader reader = null;
        String url = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open("debugging_prefs.properties");
            reader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String s = reader.readLine();
                if (s == null) {
                    break;
                }
                if (s.startsWith("url=")) {
                    url = s.substring(4).trim();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // O.K., we will use the production server
            return null;
        } catch (Exception e) {
            Log.w(TAG, "Got exception " + e);
            Log.w(TAG, Log.getStackTraceString(e));
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.w(TAG, "Got exception " + e);
                    Log.w(TAG, Log.getStackTraceString(e));
                }
            }
        }

        return url;
    }

    /**
     * Returns the package name of this class.
     */
    private static String getPackageName() {
        return Util.class.getPackage().getName();
    }
}
