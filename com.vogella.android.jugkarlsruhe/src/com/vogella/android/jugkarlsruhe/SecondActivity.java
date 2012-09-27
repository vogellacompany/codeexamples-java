package com.vogella.android.jugkarlsruhe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String string = extras.getString(Intent.EXTRA_TEXT);
		TextView view = (TextView) findViewById(R.id.textView1);
		view.setText(string);
	}

}
