package de.vogella.android.socialapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class OverviewActivity extends Activity {
	private static final String TAG = "de.vogella.android.socialapp";
	SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.Button01);
		// Initialize preferences
		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String username = preferences.getString("username", "n/a");
				String password = preferences.getString("password", "n/a");
				showPrefs(username, password);
			}
		});

		Button buttonChangePreferences = (Button) findViewById(R.id.Button02);
		buttonChangePreferences.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				updatePreferenceValue();
			}
		});
	}

	private void showPrefs(String username, String password) {
		Toast.makeText(OverviewActivity.this,
				"Input: " + username + " and password: " + password,
				Toast.LENGTH_LONG).show();

	}

	private void updatePreferenceValue() {
		Editor edit = preferences.edit();
		String username = preferences.getString("username", "n/a");
		// We will just revert the current user name and save again
		StringBuffer buffer = new StringBuffer();
		for (int i = username.length() - 1; i >= 0; i--) {
			buffer.append(username.charAt(i));
		}
		edit.putString("username", buffer.toString());
		edit.commit();
		// A toast is a view containing a quick little message for the
		// user. We give a little feedback
		Toast.makeText(OverviewActivity.this,
				"Reverted string sequence of user name.", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		MenuItem item = menu.findItem(R.id.menu_share);
		ShareActionProvider provider = (ShareActionProvider) item
				.getActionProvider();
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello");
		provider.setShareIntent(intent);
		return true;
	}

	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(TAG, "OptionsMenu called");
		switch (item.getItemId()) {
		case R.id.preferences:
			// Launch Preference activity
			Intent i = new Intent(OverviewActivity.this,
					MyPreferencesActivity.class);
			startActivity(i);
			// Some feedback to the user
			Toast.makeText(OverviewActivity.this,
					"Enter your user credentials.", Toast.LENGTH_LONG).show();
			break;
		// If home icon is clicked return to main Activity
		case android.R.id.home:
			Intent intent = new Intent(this, OverviewActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
		return true;
	}
}