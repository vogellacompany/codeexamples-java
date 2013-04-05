package com.vogella.android.nordiccoding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondlayout);
		Intent intent = getIntent();
		Bundle k = intent.getExtras();
		String string = k.getString(Intent.EXTRA_SUBJECT);
		EditText textView = (EditText) findViewById(R.id.editText1);
		textView.setText(string);
	}

}
