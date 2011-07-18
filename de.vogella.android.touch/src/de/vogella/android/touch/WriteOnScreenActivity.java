package de.vogella.android.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class WriteOnScreenActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new TouchEventView(this, null));
		System.out.println("This is a log message");
		Log.e("Mein Tag", "Hello");
	}
}