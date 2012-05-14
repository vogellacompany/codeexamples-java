package com.vogella.fraunhofer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		// Intent intent = new Intent(this, NewActivity.class);
		// startActivity(intent);
		Intent intent = new Intent("HUBBA");
		intent.putExtra("ksdfkdskf", "hello");
		intent.setType("text/plain");
		startActivity(intent);
	}
}