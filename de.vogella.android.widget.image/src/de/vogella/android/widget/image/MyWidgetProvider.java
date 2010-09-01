package de.vogella.android.widget.image;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class MyWidgetProvider extends AppWidgetProvider  {

	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int i : appWidgetIds) {
			int number = (new Random().nextInt());
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
}
