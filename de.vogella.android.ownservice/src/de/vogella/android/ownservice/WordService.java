package de.vogella.android.ownservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WordService extends Service {
	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 5000;
	private final IBinder mBinder = new MyBinder();

	public void onCreate() {
		super.onCreate();
		pollForUpdates();
	}

	private void pollForUpdates() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

			}
		}, 0, UPDATE_INTERVAL);
		Log.i(getClass().getSimpleName(), "Timer started.");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
		Log.i(getClass().getSimpleName(), "Timer stopped.");

	}

	/** we do not use IPC therefore the onBind is not important for us */
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		WordService getService() {
			return WordService.this;
		}
	}

	public List<String> getWordList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Linux");
		list.add("Linux");
		return list;
	}

}
