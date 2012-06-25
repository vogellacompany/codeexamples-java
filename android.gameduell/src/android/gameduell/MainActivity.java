package android.gameduell;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
			Toast.makeText(this, "Hello Gameduell", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			Intent i = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.de"));
			startActivity(i);
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
}