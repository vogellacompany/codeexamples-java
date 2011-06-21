package de.vogella.android.widget.example;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// Get some useful info from the web
		String fakeUpdate = "Random: ";
		Random random = new Random();
		int nextInt = random.nextInt(100);
		fakeUpdate += String.valueOf(nextInt);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		remoteViews.setTextViewText(R.id.TextView01, fakeUpdate);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}
}
