package com.vogella.android.nordiccoding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		Intent k = new Intent(Intent.ACTION_SEND);
		k.setType("text/plain");
		k.putExtra(Intent.EXTRA_SUBJECT, "Ich komme aus Kiel, lieber Sven");
		startActivity(k);
	}
}
