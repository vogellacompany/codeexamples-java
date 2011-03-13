package de.vogella.android.c2dm.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ServerSimulator extends Activity {
	private SharedPreferences prefManager;
	private final static String AUTH = "authentication";

	private static final String UPDATE_CLIENT_AUTH = "Update-Client-Auth";

	public static final String PARAM_REGISTRATION_ID = "registration_id";

	public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";

	public static final String PARAM_COLLAPSE_KEY = "collapse_key";

	private static final String UTF8 = "UTF-8";

	// Registration is currently hardcoded
	private final static String YOUR_REGISTRATION_STRING = "APA91bFQut1tqA-nIL1ZaV0emnp4Rb0smwCkrMHcoYRXeYVtIebJgrzOHQj0h76qKRzd3bC_JO37uJ0NgTcFO87HS9V7YC-yOP774pm0toppTHFO7Zc_PAw";

	private SharedPreferences prefs;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		prefManager = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem_user:
			Intent intent = new Intent(this, UserPreferences.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return false;
	}

	public void getAuthentification(View view) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"https://www.google.com/accounts/ClientLogin");

		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("Email", prefs.getString(
					"user", "undefined")));
			nameValuePairs.add(new BasicNameValuePair("Passwd", prefs
					.getString("password", "undefined")));
			nameValuePairs.add(new BasicNameValuePair("accountType", "GOOGLE"));
			nameValuePairs.add(new BasicNameValuePair("source",
					"Google-cURL-Example"));
			nameValuePairs.add(new BasicNameValuePair("service", "ac2dm"));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				Log.e("HttpResponse", line);
				if (line.startsWith("Auth=")) {
					Editor edit = prefManager.edit();
					edit.putString(AUTH, line.substring(5));
					edit.commit();
					String s = prefManager.getString(AUTH, "n/a");
					Toast.makeText(this, s, Toast.LENGTH_LONG).show();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAuthentification(View view) {
		String s = prefManager.getString(AUTH, "n/a");
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}

	public void sendMessage(View view) {
		try {
			Log.e("Tag", "Started");
			String auth_key = prefManager.getString(AUTH, "n/a");
			// Send a sync message to this Android device.
			StringBuilder postDataBuilder = new StringBuilder();
			postDataBuilder.append(PARAM_REGISTRATION_ID).append("=")
					.append(YOUR_REGISTRATION_STRING);

			// if (delayWhileIdle) {
			// postDataBuilder.append("&").append(PARAM_DELAY_WHILE_IDLE)
			// .append("=1");
			// }
			postDataBuilder.append("&").append(PARAM_COLLAPSE_KEY).append("=")
					.append("0");

			postDataBuilder.append("&").append("data.payload").append("=")
					.append(URLEncoder.encode("Lars war hier", UTF8));

			byte[] postData = postDataBuilder.toString().getBytes(UTF8);

			// Hit the dm URL.

			URL url = new URL("https://android.clients.google.com/c2dm/send");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestProperty("Content-Length",
					Integer.toString(postData.length));
			conn.setRequestProperty("Authorization", "GoogleLogin auth="
					+ auth_key);

			OutputStream out = conn.getOutputStream();
			out.write(postData);
			out.close();

			int responseCode = conn.getResponseCode();

			Log.e("Tag", String.valueOf(responseCode));
			// Validate the response code

			if (responseCode == 401 || responseCode == 403) {
				// The token is too old - return false to retry later, will
				// fetch the token
				// from DB. This happens if the password is changed or token
				// expires. Either admin
				// is updating the token, or Update-Client-Auth was received by
				// another server,
				// and next retry will get the good one from database.
				Log.e("C2DM", "Unauthorized - need token");
			}

			// Check for updated token header
			String updatedAuthToken = conn.getHeaderField(UPDATE_CLIENT_AUTH);
			if (updatedAuthToken != null && !auth_key.equals(updatedAuthToken)) {
				Log.i("C2DM",
						"Got updated auth token from datamessaging servers: "
								+ updatedAuthToken);
				Editor edit = prefManager.edit();
				edit.putString(AUTH, updatedAuthToken);
			}

			String responseLine = new BufferedReader(new InputStreamReader(
					conn.getInputStream())).readLine();

			// NOTE: You *MUST* use exponential backoff if you receive a 503
			// response code.
			// Since App Engine's task queue mechanism automatically does this
			// for tasks that
			// return non-success error codes, this is not explicitly
			// implemented here.
			// If we weren't using App Engine, we'd need to manually implement
			// this.
			if (responseLine == null || responseLine.equals("")) {
				Log.i("C2DM", "Got " + responseCode
						+ " response from Google AC2DM endpoint.");
				throw new IOException(
						"Got empty response from Google AC2DM endpoint.");
			}

			String[] responseParts = responseLine.split("=", 2);
			if (responseParts.length != 2) {
				Log.e("C2DM", "Invalid message from google: " + responseCode
						+ " " + responseLine);
				throw new IOException("Invalid response from Google "
						+ responseCode + " " + responseLine);
			}

			if (responseParts[0].equals("id")) {
				Log.i("Tag", "Successfully sent data message to device: "
						+ responseLine);
			}

			if (responseParts[0].equals("Error")) {
				String err = responseParts[1];
				Log.w("C2DM",
						"Got error response from Google datamessaging endpoint: "
								+ err);
				// No retry.
				throw new IOException(err);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}