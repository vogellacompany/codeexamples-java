package com.vogella.fraunhofer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends Activity {
	public static final String RETURN_KEY1 = "returnKey1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		String string = extras.getString(Intent.EXTRA_TEXT);
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
		setContentView(R.layout.second);
		Intent intent = new Intent();
		Button id = (Button) findViewById(R.id.button1);

	}

	@Override
	public void finish() {
		Intent intent = new Intent();
		intent.putExtra(RETURN_KEY1, "testing");
		setResult(RESULT_OK, intent);
		super.finish();
	}

}
