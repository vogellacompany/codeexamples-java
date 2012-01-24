package com.example.oop.drawing;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class DrawingActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}
}