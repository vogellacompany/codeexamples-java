package com.example.android.rssfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RssfeedActivity extends Activity implements
		MyListFragment.OnItemSelectedListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder()
		// .permitAll().build();
		// StrictMode.setThreadPolicy(policy);

		// setContentView(R.layout.activity_rssfeed);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_refresh:
			Toast.makeText(this, "Action refresh selected", Toast.LENGTH_SHORT)
					.show();
			// MyListFragment fragment = (MyListFragment) getFragmentManager()
			// .findFragmentById(R.id.listFragment);
			break;
		case R.id.action_settings:
			Intent intent = new Intent(this, MyPreferenceActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onRssItemSelected(String link) {
		// TODO Auto-generated method stub

	}
}
