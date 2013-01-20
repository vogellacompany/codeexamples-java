package com.example.com.vogella.android.actionbar.actionprovider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
	private ShareActionProvider provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);

		// Get the ActionProvider
		provider = (ShareActionProvider) menu.findItem(R.id.menu_share)
				.getActionProvider();
		// Initialize the share intent
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		provider.setShareIntent(intent);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_share:
			doShare();
			break;
		default:
			break;
		}
		return true;
	}

	public void doShare() {
		// Populare the share intent with data
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
		provider.setShareIntent(intent);
	}

}
