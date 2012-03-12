package android.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.button1:
			// Intent i = new Intent(Intent.ACTION_SEND);
			// i.setType("text/plain");
			// i.putExtra(Intent.EXTRA_TEXT, "Schulung ist in Zeit");
			// startActivity(i);
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(this);
			String string = prefs.getString("user", "unsinn...");
			Toast.makeText(this, string, Toast.LENGTH_LONG).show();
			Editor edit = prefs.edit();
			edit.putString("user", "vogella2");
			edit.commit();
			break;
		case R.id.button2:
			Intent intent = new Intent(this, SecondActivity.class);
			startActivityForResult(intent, 10);
			break;

		case R.id.button3:
			downloadwebpage();
			break;

		default:
			break;
		}
	}

	private void downloadwebpage() {
		Toast.makeText(this, "Downloading Webpage", Toast.LENGTH_LONG).show();

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 10:
			Toast.makeText(this, "Result", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
}