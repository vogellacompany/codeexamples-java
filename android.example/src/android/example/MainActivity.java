package android.example;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i("TAG", "onCreate called");

	}

	public void onClick(View view) {
		// Intent intent = new Intent(Intent.ACTION_SEND);
		// intent.setType("text/plain");
		// intent.putExtra(Intent.EXTRA_TEXT, "This is my message");
		// startActivity(intent);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String string = prefs.getString("user", "not known");
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Intent i = new Intent(this, MyPreferenceActivity.class);
			startActivity(i);
			break;
		case R.id.item2:
			Intent intent = new Intent(this, SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.item3:

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}