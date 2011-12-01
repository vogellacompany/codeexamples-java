package com.example.devoxx;

import android.app.Activity;
import android.os.Bundle;

public class MyPreferenceActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawingView(this));
	}
}
