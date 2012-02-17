package jfokus.training.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);

		ShareActionProvider provider = (ShareActionProvider) menu.findItem(
				R.id.menu_share).getActionProvider();
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		provider.setShareIntent(intent);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String string = prefs.getString("user", "not maintained");
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);

	}
}