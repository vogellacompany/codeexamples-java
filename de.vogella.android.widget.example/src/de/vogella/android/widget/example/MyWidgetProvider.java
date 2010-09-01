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
		for (int i : appWidgetIds) {
			int number = (new Random().nextInt(100));
			System.out.println("Called");
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			views.setTextViewText(R.id.message, String.valueOf(number));
			appWidgetManager.updateAppWidget(i, views);
		}
	}
}
