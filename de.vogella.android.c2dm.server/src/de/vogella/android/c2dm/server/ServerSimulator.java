package de.vogella.android.c2dm.server;

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
	private final static String YOUR_REGISTRATION_STRING = "APA91bGEkMCz6k8_IVdQMucDDHP0GcEj3RpTDnqsQ1DgXq19LTsjYfi66kk3Wuw2Bk7NZw1q2EgZA5wnzEP28qNswNLJpMWJ9wsV5HA1Gntv1ZAjg3x5zlk";

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
			nameValuePairs.add(new BasicNameValuePair("Passwd", prefs.getString(
					"password", "undefined")));
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
		HttpClient client = new DefaultHttpClient();
		String authenticationString = prefManager.getString(AUTH, "n/a");
		HttpPost post = new HttpPost(
				"https://android.apis.google.com/c2dm/send");
		final String HEADER = "Authorization: GoogleLogin auth="
				+ authenticationString;
		Toast.makeText(this, prefManager.getString(AUTH, "n/a"),
				Toast.LENGTH_LONG).show();
		final String VALUE = "https://android.apis.google.com/c2dm/send";
		post.setHeader(HEADER, VALUE);
		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("registration_id",
					YOUR_REGISTRATION_STRING));
			nameValuePairs.add(new BasicNameValuePair("collapse_key", "0"));
			nameValuePairs.add(new BasicNameValuePair("data.payload",
					"Lars war hier"));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				Log.e("sendResponse", line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}