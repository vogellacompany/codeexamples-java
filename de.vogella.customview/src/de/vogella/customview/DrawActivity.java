package de.vogella.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DrawActivity extends Activity {

	private int clicks;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (savedInstanceState != null) {
			clicks = savedInstanceState.getInt("clicks", 0);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("clicks", clicks);
		super.onSaveInstanceState(outState);
	}

	public void onClick(View view) {
		view.setBackgroundColor(0xBBFF0000);

		clicks++;
		Toast.makeText(this, "Counter: " + clicks, Toast.LENGTH_SHORT).show();
	}

}