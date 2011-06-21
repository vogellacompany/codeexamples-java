package de.vogella.android.widget.example;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	@Override
	public void onStart(Intent intent, int startId) {
		int[] intArrayExtra = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
		if (intArrayExtra.length > 0) {
			Log.i("UpdateWidgetService", "Called");
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(this.getApplicationContext());
			RemoteViews remoteViews = new RemoteViews(getPackageName(),
					R.layout.widget_layout);
			remoteViews.setTextViewText(R.id.TextView01, "New text");
			appWidgetManager.updateAppWidget(intArrayExtra, remoteViews);
			stopSelf();
		}
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
