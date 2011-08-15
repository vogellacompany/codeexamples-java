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

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

/**
 * Display a message as a notification, with an accompanying sound.
 */
public class MessageDisplay {

    private MessageDisplay() {
    }

    /*
     * App-specific methods for the sample application - 1) parse the incoming
     * message; 2) generate a notification; 3) play a sound
     */

    public static void displayMessage(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String sender = (String) extras.get("sender");
            String message = (String) extras.get("message");
            Util.generateNotification(context, "Message from " + sender + ": " + message);
            playNotificationSound(context);
        }
    }

    private static void playNotificationSound(Context context) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (uri != null) {
            Ringtone rt = RingtoneManager.getRingtone(context, uri);
            if (rt != null) {
                rt.setStreamType(AudioManager.STREAM_NOTIFICATION);
                rt.play();
            }
        }
    }
}
