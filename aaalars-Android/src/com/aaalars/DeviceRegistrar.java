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

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import com.aaalars.client.MyRequestFactory;
import com.aaalars.client.MyRequestFactory.RegistrationInfoRequest;
import com.aaalars.shared.RegistrationInfoProxy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;
import android.util.Log;

/**
 * Register/unregister with the third-party App Engine server using
 * RequestFactory.
 */
public class DeviceRegistrar {
    public static final String ACCOUNT_NAME_EXTRA = "AccountName";

    public static final String STATUS_EXTRA = "Status";

    public static final int REGISTERED_STATUS = 1;

    public static final int UNREGISTERED_STATUS = 2;

    public static final int ERROR_STATUS = 3;

    private static final String TAG = "DeviceRegistrar";

    public static void registerOrUnregister(final Context context,
            final String deviceRegistrationId, final boolean register) {
        final SharedPreferences settings = Util.getSharedPreferences(context);
        final String accountName = settings.getString(Util.ACCOUNT_NAME, "Unknown");
        final Intent updateUIIntent = new Intent(Util.UPDATE_UI_INTENT);

        RegistrationInfoRequest request = getRequest(context);
        RegistrationInfoProxy proxy = request.create(RegistrationInfoProxy.class);
        proxy.setDeviceRegistrationId(deviceRegistrationId);

        String deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        proxy.setDeviceId(deviceId);

        Request<Void> req;
        if (register) {
            req = request.register().using(proxy);
        } else {
            req = request.unregister().using(proxy);
        }

        req.fire(new Receiver<Void>() {
            private void clearPreferences(SharedPreferences.Editor editor) {
                editor.remove(Util.ACCOUNT_NAME);
                editor.remove(Util.AUTH_COOKIE);
                editor.remove(Util.DEVICE_REGISTRATION_ID);
            }

            @Override
            public void onFailure(ServerFailure failure) {
                Log.w(TAG, "Failure, got :" + failure.getMessage());
                // Clean up application state
                SharedPreferences.Editor editor = settings.edit();
                clearPreferences(editor);
                editor.commit();

                updateUIIntent.putExtra(ACCOUNT_NAME_EXTRA, accountName);
                updateUIIntent.putExtra(STATUS_EXTRA, ERROR_STATUS);
                context.sendBroadcast(updateUIIntent);
            }

            @Override
            public void onSuccess(Void response) {
                SharedPreferences settings = Util.getSharedPreferences(context);
                SharedPreferences.Editor editor = settings.edit();
                if (register) {
                    editor.putString(Util.DEVICE_REGISTRATION_ID, deviceRegistrationId);
                } else {
                    clearPreferences(editor);
                }
                editor.commit();
                updateUIIntent.putExtra(ACCOUNT_NAME_EXTRA, accountName);
                updateUIIntent.putExtra(STATUS_EXTRA, register ? REGISTERED_STATUS
                        : UNREGISTERED_STATUS);
                context.sendBroadcast(updateUIIntent);
            }
        });
    }

    private static RegistrationInfoRequest getRequest(Context context) {
        MyRequestFactory requestFactory = Util.getRequestFactory(context, MyRequestFactory.class);
        RegistrationInfoRequest request = requestFactory.registrationInfoRequest();
        return request;
    }
}
