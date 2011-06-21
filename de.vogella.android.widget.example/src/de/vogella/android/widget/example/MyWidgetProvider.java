package de.vogella.android.widget.example;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// Get some useful info from the web
		String fakeUpdate = "Updating the text...";
		Random random = new Random();
		int nextInt = random.nextInt(100);
		fakeUpdate += String.valueOf(nextInt);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		Intent intent = new Intent(context.getApplicationContext(),
				UpdateWidgetService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
		PendingIntent pendingIntent = PendingIntent.getService(
				context.getApplicationContext(), 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);
		remoteViews.setTextViewText(R.id.TextView01, fakeUpdate);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

		// context.startService(intent);
	}
}
