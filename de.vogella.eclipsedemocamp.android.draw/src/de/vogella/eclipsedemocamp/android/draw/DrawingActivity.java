package de.vogella.eclipsedemocamp.android.draw;

import android.app.Activity;
import android.os.Bundle;

public class DrawingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawingView(this));
	}
}
