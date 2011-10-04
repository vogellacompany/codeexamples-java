package de.vogella.android.c2dm.simpleclient;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class C2DMClientActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void register(View view) {
		Log.w("C2DM", "start registration process");
		Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
		intent.putExtra("app",
				PendingIntent.getBroadcast(this, 0, new Intent(), 0));
		// Sender currently not used
		intent.putExtra("sender", "nonsenses@gmail.com");
		startService(intent);
	}
}