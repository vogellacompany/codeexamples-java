package com.vogella.microdrones.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			if (BuildConfig.DEBUG) {
				Log.i("Mein Tag", "Hello");
			}
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "Das ist mein Header");
			startActivityForResult(intent, 10);
			break;
		case R.id.button2:
			Intent i = new Intent(this, SecondActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	}
}
