package de.vogella.android.test.target;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SimpleActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void clickHandler(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Intent intent = new Intent(this, SimpleListActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_LONG).show();
			break;
		case R.id.button3:
			Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			Intent intent = new Intent(this, PreferencePage.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}