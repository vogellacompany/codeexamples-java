package de.vogella.android.c2dm.simpleclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.util.Log;

public class C2DMRegistrationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.w("C2DM", "Registration Receiver called");
		if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
			Log.w("C2DM", "Received registration ID");
			final String registrationId = intent
					.getStringExtra("registration_id");
			String error = intent.getStringExtra("error");

			Log.d("C2DM", "dmControl: registrationId = " + registrationId
					+ ", error = " + error);
			String deviceId = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
			sendRegistrationIdToServer(deviceId, registrationId);
		}
	}

	// Better do this in an asynchronous thread
	public void sendRegistrationIdToServer(String deviceId,
			String registrationId) {
		Log.d("C2DM", "Sending registration ID to my application server");
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://vogellac2dm.appspot.com/register");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			// Get the deviceID
			nameValuePairs.add(new BasicNameValuePair("deviceid", deviceId));
			nameValuePairs.add(new BasicNameValuePair("registrationid",
					registrationId));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				Log.e("HttpResponse", line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
