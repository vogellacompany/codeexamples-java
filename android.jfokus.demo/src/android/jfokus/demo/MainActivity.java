package android.jfokus.demo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private ShareActionProvider provider;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageView lightBulb = (ImageView) findViewById(R.id.imageView2);
		final ToggleButton lightSwitch = (ToggleButton) findViewById(R.id.toggleButton1);
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

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Intent intent = new Intent(this, NextActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Notification notification = new Notification(
					R.drawable.ic_launcher, "A new notification",
					System.currentTimeMillis());
			// Hide the notification after its selected
			notification.flags |= Notification.FLAG_AUTO_CANCEL;

			intent = new Intent(this, NextActivity.class);
			PendingIntent activity = PendingIntent.getActivity(this, 0, intent,
					0);
			notification.setLatestEventInfo(this, "This is the title",
					"This is the text", activity);
			notification.number += 1;
			notificationManager.notify(0, notification);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		provider = (ShareActionProvider) menu.findItem(R.id.menu_share)
				.getActionProvider();
		doShare();
		return true;
	}

	public void doShare() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Message");
		provider.setShareIntent(intent);
	}

	public int convertToDp(int input) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (input * scale + 0.5f);
	}

}