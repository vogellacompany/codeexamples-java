package de.vogella.android.codecamp;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyDrawView(this));
		MyApplication application = (MyApplication) getApplication();
	}
}