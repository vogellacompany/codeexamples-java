package de.vogella.android.widget.example;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProviderSimple extends AppWidgetProvider {

	// TODO 0 - add missing intent filter for the the widget in
	// AndroidManifest.xml
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		// Get all ids
		ComponentName thisWidget = new ComponentName(context,
				MyWidgetProviderSimple.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		for (int widgetId : allWidgetIds) {
			// Create some random data
			int number = (new Random().nextInt(100));

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widgetsimple_layout);
			Log.w("WidgetExample", String.valueOf(number));
			// TODO 1 - Set the number t o the view with the id R.id.update

			// TODO 2 - Create Intent which will call again
			// MyWidgetProviderSimple
			Intent intent = null;

			// TODO 3 - Comment these two lines
			// intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			// intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
			// allWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
}