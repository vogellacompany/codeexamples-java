package com.vogella.android.customview.persistence;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("key1", "15");
		super.onSaveInstanceState(outState);
	}

}
