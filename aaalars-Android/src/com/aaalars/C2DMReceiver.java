/*
 * Copyright 2010 Google Inc.
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

import com.google.android.c2dm.C2DMBaseReceiver;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Receive a push message from the Cloud to Device Messaging (C2DM) service.
 * This class should be modified to include functionality specific to your
 * application. This class must have a no-arg constructor and pass the sender id
 * to the superclass constructor.
 */
public class C2DMReceiver extends C2DMBaseReceiver {

    public C2DMReceiver() {
        super(Setup.SENDER_ID);
    }

    /**
     * Called when a registration token has been received.
     * 
     * @param context the Context
     * @param registrationId the registration id as a String
     * @throws IOException if registration cannot be performed
     */
    @Override
    public void onRegistered(Context context, String registration) {
        DeviceRegistrar.registerOrUnregister(context, registration, true);
    }

    /**
     * Called when the device has been unregistered.
     * 
     * @param context the Context
     */
    @Override
    public void onUnregistered(Context context) {
        SharedPreferences prefs = Util.getSharedPreferences(context);
        String deviceRegistrationID = prefs.getString(Util.DEVICE_REGISTRATION_ID, null);
        DeviceRegistrar.registerOrUnregister(context, deviceRegistrationID, false);
    }

    /**
     * Called on registration error. This is called in the context of a Service
     * - no dialog or UI.
     * 
     * @param context the Context
     * @param errorId an error message, defined in {@link C2DMBaseReceiver}
     */
    @Override
    public void onError(Context context, String errorId) {
        context.sendBroadcast(new Intent(Util.UPDATE_UI_INTENT));
    }

    /**
     * Called when a cloud message has been received.
     */
    @Override
    public void onMessage(Context context, Intent intent) {
        /*
         * Replace this with your application-specific code
         */
        MessageDisplay.displayMessage(context, intent);
    }
}
