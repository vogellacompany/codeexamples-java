package de.vogella.android.oop.demo;

import android.app.Activity;
import android.os.Bundle;

public class OopActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}
}