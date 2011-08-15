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

import com.google.android.c2dm.C2DMessaging;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences.Editor;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Account selections activity - handles device registration and unregistration.
 */
public class AccountsActivity extends Activity {

    /**
     * Tag for logging.
     */
    private static final String TAG = "AccountsActivity";

    /**
     * Cookie name for authorization.
     */
    private static final String AUTH_COOKIE_NAME = "SACSID";

    /**
     * The selected position in the ListView of accounts.
     */
    private int mAccountSelectedPosition = 0;

    /**
     * True if we are waiting for App Engine authorization.
     */
    private boolean mPendingAuth = false;

    /**
     * The current context.
     */
    private Context mContext = this;

    /**
     * Begins the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = Util.getSharedPreferences(mContext);
        String deviceRegistrationID = prefs.getString(Util.DEVICE_REGISTRATION_ID, null);
        if (deviceRegistrationID == null) {
            // Show the 'connect' screen if we are not connected
            setScreenContent(R.layout.connect);
        } else {
            // Show the 'disconnect' screen if we are connected
            setScreenContent(R.layout.disconnect);
        }
    }

    /**
     * Resumes the activity.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mPendingAuth) {
            mPendingAuth = false;
            String regId = C2DMessaging.getRegistrationId(mContext);
            if (regId != null && !"".equals(regId)) {
                DeviceRegistrar.registerOrUnregister(mContext, regId, true);
            } else {
                C2DMessaging.register(mContext, Setup.SENDER_ID);
            }
        }
    }

    // Manage UI Screens

    /**
     * Sets up the 'connect' screen content.
     */
    private void setConnectScreenContent() {
        List<String> accounts = getGoogleAccounts();
        if (accounts.size() == 0) {
            // Show a dialog and invoke the "Add Account" activity if requested
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.needs_account);
            builder.setPositiveButton(R.string.add_account, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_ADD_ACCOUNT));
                }
            });
            builder.setNegativeButton(R.string.skip, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setTitle(R.string.attention);
            builder.show();
        } else {
            final ListView listView = (ListView) findViewById(R.id.select_account);
            listView.setAdapter(new ArrayAdapter<String>(mContext, R.layout.account, accounts));
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listView.setItemChecked(mAccountSelectedPosition, true);

            final Button connectButton = (Button) findViewById(R.id.connect);
            connectButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // Set "connecting" status
                    SharedPreferences prefs = Util.getSharedPreferences(mContext);
                    prefs.edit().putString(Util.CONNECTION_STATUS, Util.CONNECTING).commit();
                    // Get account name
                    mAccountSelectedPosition = listView.getCheckedItemPosition();
                    TextView account = (TextView) listView.getChildAt(mAccountSelectedPosition);
                    // Register
                    register((String) account.getText());
                    finish();
                }
            });
        }
    }

    /**
     * Sets up the 'disconnected' screen.
     */
    private void setDisconnectScreenContent() {
        final SharedPreferences prefs = Util.getSharedPreferences(mContext);
        String accountName = prefs.getString(Util.ACCOUNT_NAME, "Unknown");

        // Format the disconnect message with the currently connected account
        // name
        TextView disconnectText = (TextView) findViewById(R.id.disconnect_text);
        String message = getResources().getString(R.string.disconnect_text);
        String formatted = String.format(message, accountName);
        disconnectText.setText(formatted);

        final Button disconnectButton = (Button) findViewById(R.id.disconnect);
        disconnectButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Unregister
                C2DMessaging.unregister(mContext);
                finish();
            }
        });
    }

    /**
     * Sets the screen content based on the screen id.
     */
    private void setScreenContent(int screenId) {
        setContentView(screenId);
        switch (screenId) {
            case R.layout.disconnect:
                setDisconnectScreenContent();
                break;
            case R.layout.connect:
                setConnectScreenContent();
                break;
        }
    }

    // Register and Unregister

    /**
     * Registers for C2DM messaging with the given account name.
     * 
     * @param accountName a String containing a Google account name
     */
    private void register(final String accountName) {
        // Store the account name in shared preferences
        final SharedPreferences prefs = Util.getSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Util.ACCOUNT_NAME, accountName);
        editor.remove(Util.AUTH_COOKIE);
        editor.remove(Util.DEVICE_REGISTRATION_ID);
        editor.commit();

        // Obtain an auth token and register
        final AccountManager mgr = AccountManager.get(mContext);
        Account[] accts = mgr.getAccountsByType("com.google");
        for (Account acct : accts) {
            final Account account = acct;
            if (account.name.equals(accountName)) {
                if (Util.isDebug(mContext)) {
                    // Use a fake cookie for the dev mode app engine server
                    // The cookie has the form email:isAdmin:userId
                    // We set the userId to be the same as the email
                    String authCookie = "dev_appserver_login=" + accountName + ":false:"
                            + accountName;
                    prefs.edit().putString(Util.AUTH_COOKIE, authCookie).commit();
                    C2DMessaging.register(mContext, Setup.SENDER_ID);
                } else {
                    // Get the auth token from the AccountManager and convert
                    // it into a cookie for the appengine server
                    final Activity activity = this;
                    mgr.getAuthToken(account, "ah", null, activity, new AccountManagerCallback<Bundle>() {
                        public void run(AccountManagerFuture<Bundle> future) {
                            String authToken = getAuthToken(future);
                            // Ensure the token is not expired by invalidating it and
                            // obtaining a new one
                            mgr.invalidateAuthToken(account.type, authToken);
                            mgr.getAuthToken(account, "ah", null, activity, new AccountManagerCallback<Bundle>() {
                                public void run(AccountManagerFuture<Bundle> future) {
                                    String authToken = getAuthToken(future);
                                    // Convert the token into a cookie for future use
                                    String authCookie = getAuthCookie(authToken);
                                    Editor editor = prefs.edit();
                                    editor.putString(Util.AUTH_COOKIE, authCookie);
                                    editor.commit();
                                    C2DMessaging.register(mContext, Setup.SENDER_ID);
                                }
                            }, null);
                        }
                    }, null);
                }
                break;
            }
        }
    }

    private String getAuthToken(AccountManagerFuture<Bundle> future) {
        try {
            Bundle authTokenBundle = future.getResult();
            String authToken = authTokenBundle.get(AccountManager.KEY_AUTHTOKEN).toString();
            return authToken;
        } catch (Exception e) {
            Log.w(TAG, "Got Exception " + e);
            return null;
        }
    }

    // Utility Methods

    /**
     * Retrieves the authorization cookie associated with the given token. This
     * method should only be used when running against a production appengine
     * backend (as opposed to a dev mode server).
     */
    private String getAuthCookie(String authToken) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            // Get SACSID cookie
            httpClient.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);
            String uri = Setup.PROD_URL + "/_ah/login?continue=http://localhost/&auth=" + authToken;
            HttpGet method = new HttpGet(uri);

            HttpResponse res = httpClient.execute(method);
            StatusLine statusLine = res.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            Header[] headers = res.getHeaders("Set-Cookie");
            if (statusCode != 302 || headers.length == 0) {
                return null;
            }

            for (Cookie cookie : httpClient.getCookieStore().getCookies()) {
                if (AUTH_COOKIE_NAME.equals(cookie.getName())) {
                    return AUTH_COOKIE_NAME + "=" + cookie.getValue();
                }
            }
        } catch (IOException e) {
            Log.w(TAG, "Got IOException " + e);
            Log.w(TAG, Log.getStackTraceString(e));
        } finally {
            httpClient.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true);
        }

        return null;
    }

    /**
     * Returns a list of registered Google account names. If no Google accounts
     * are registered on the device, a zero-length list is returned.
     */
    private List<String> getGoogleAccounts() {
        ArrayList<String> result = new ArrayList<String>();
        Account[] accounts = AccountManager.get(mContext).getAccounts();
        for (Account account : accounts) {
            if (account.type.equals("com.google")) {
                result.add(account.name);
            }
        }

        return result;
    }
}
