package com.example.devoxx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class OverviewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Toast.makeText(this, "Hello Devoxx", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.perference:
			// Start the activity to maintain the use
			Intent intent = new Intent(this, MyPreferenceActivity.class);
			startActivity(intent);
			break;
		case R.id.startSecondActivity:
			intent = new Intent(this, MyListActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return true;
	}
}