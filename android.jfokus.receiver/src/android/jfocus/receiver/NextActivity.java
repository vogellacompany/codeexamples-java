package android.jfocus.receiver;

import android.app.Activity;
import android.os.Bundle;

public class NextActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String s1 = extras.getString("key1");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("test", true);
		super.onSaveInstanceState(outState);
	}
}
