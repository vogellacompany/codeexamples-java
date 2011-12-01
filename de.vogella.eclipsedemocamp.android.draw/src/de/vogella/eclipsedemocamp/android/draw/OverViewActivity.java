package de.vogella.eclipsedemocamp.android.draw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OverViewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.imageView1:
			Intent intent = new Intent(this, DrawingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}