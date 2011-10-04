package de.vogella.android.c2dm.simpleclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class C2DMMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.w("C2DM", "Message Receiver called");
		if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
			Log.w("C2DM", "Received message");
			final String payload = intent.getStringExtra("payload");
			Log.d("C2DM", "dmControl: payload = " + payload);
			// TODO Send this to my application server to get the real data
		}
	}
}
