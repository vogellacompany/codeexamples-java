package de.vogella.android.widget.followers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class TwitterFollowerService extends Service {

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i("TwitterFollowerService", "Called");
		SharedPreferences preferences = this.getSharedPreferences(
				TwitterFollowerConfigActivity.PREFS_NAME, 0);

		int[] appWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		if (appWidgetIds.length > 0) {

			for (int widgetId : appWidgetIds) {
				try {
					String user = preferences.getString(
							TwitterFollowerConfigActivity.PREF_PREFIX_KEY
									+ widgetId, "vogella");
					JSONObject twitterProfile = new JSONObject(
							readTwitterFeed(user));
					Log.e("TwitterFollowerReceiver", String
							.valueOf(twitterProfile.getInt("followers_count")));
					RemoteViews views = new RemoteViews(this.getPackageName(),
							R.layout.widget_layout);
					Date dt = new Date();
					int hours = dt.getHours();
					int minutes = dt.getMinutes();
					String sMinutes = String.valueOf(minutes);
					if (sMinutes.length() == 1) {
						sMinutes = "0" + sMinutes;
					}

					String curTime = hours + ":" + sMinutes;
					views.setTextViewText(R.id.twitteruser, user);
					views.setTextViewText(R.id.followers, String
							.valueOf(twitterProfile.getInt("followers_count")));
					views.setTextViewText(R.id.time, String.valueOf(curTime));
					appWidgetManager.updateAppWidget(widgetId, views);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			stopSelf();
		}
		super.onStart(intent, startId);

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private String readTwitterFeed(String user) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://twitter.com/users/show/" + user
				+ ".json");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e("TwitterFollowerReceiver", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

}
