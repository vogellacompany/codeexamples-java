package de.vogella.android.widget.image;

import java.util.List;

import net.unto.twitter.Api;
import net.unto.twitter.TwitterProtos;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		// for all widgets which are served by this provider
		for (int i = 0; i < N; i++) {
			int j = appWidgetIds[i];
			Api api = Api.builder().username("vogellabot")
					.password("playing14").build();
			api.verifyCredentials();
//			List<TwitterProtos.User> friends = api.friends().build().get();
//			friends.size();

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.hellowidget_layout);
			views.setTextViewText(R.id.TextView01, "5");
			appWidgetManager.updateAppWidget(j, views);

		}

	}
}
