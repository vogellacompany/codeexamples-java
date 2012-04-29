package de.vogella.android.activitiystack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NextActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		Intent intent = new Intent(this, NextNextActivity.class);
		startActivity(intent);
	}
}
