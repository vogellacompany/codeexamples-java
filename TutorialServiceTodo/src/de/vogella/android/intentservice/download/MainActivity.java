package de.vogella.android.intentservice.download;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			Bundle data = message.getData();
			if (message.arg1 == RESULT_OK && data != null) {
				// TODO 5 - get the path from the service and show as Toast
			} else {
				Toast.makeText(MainActivity.this, "Download failed.",
						Toast.LENGTH_LONG).show();
			}

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.startIntentService:
			intent = new Intent(this, DownloadService.class);
		default:
			break;
		}
		// Create a new Messenger for the communication back
		Messenger messenger = new Messenger(handler);

		// TODO 2 - Put the instance of Messenger into the Intent as Extra, use
		// the key "MESSENGER"

		// Setting the data for the service to work on
		intent.setData(Uri.parse("http://www.vogella.de/index.html"));
		intent.putExtra("urlpath", "http://www.vogella.de/index.html");

		// TODO 3 - start the service

	}

	public void showToast(View view) {
		Toast.makeText(this, "Still interactive", Toast.LENGTH_SHORT).show();
	}
}