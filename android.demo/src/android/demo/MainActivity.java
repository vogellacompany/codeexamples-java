package android.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {

		Intent intent = new Intent(this, NextActivity.class);
		intent.putExtra("key1", "Hello");
		startActivityForResult(intent, 10);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == 10) {
			Toast.makeText(this, "Got results", Toast.LENGTH_LONG).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, "This is a text");
			intent.setType("text/plain");
			startActivity(intent);
			break;
		case R.id.item2:
			Intent i = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.vogella.de"));
			startActivity(i);
			break;
		default:
			break;
		}
		return true;
	}
}