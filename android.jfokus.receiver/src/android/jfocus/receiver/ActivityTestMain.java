package android.jfocus.receiver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityTestMain extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ImageView lightBulb = (ImageView) findViewById(R.id.imageView2);
		final ToggleButton lightSwitch = (ToggleButton) findViewById(R.id.imageSwitcher);
		lightSwitch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				TransitionDrawable drawable = (TransitionDrawable) lightBulb
						.getDrawable();
				if (lightSwitch.isChecked()) {
					drawable.startTransition(1000);
				} else {
					drawable.reverseTransition(1000);
				}
			}
		});

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
		case R.id.item1:
			Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_LONG).show();
			break;
		case R.id.item2:

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "Practice, Practice, Practice");
			intent.putExtra(Intent.EXTRA_TITLE, "Hello");
			startActivity(intent);
			break;

		case R.id.button2:
			Intent i = new Intent(this, ActivityTest.class);
			startActivity(i);
			break;
		default:
			break;
		}
		// Intent.createChooser(intent, "Please select");
	}
}