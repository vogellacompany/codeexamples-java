package de.vogella.android.ownservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class ServiceConsumer extends Activity {
	private WordService s;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		doBindService();
		// List<String> wordList = s.getWordList();
		// Toast.makeText(this, wordList.get(0), Toast.LENGTH_LONG).show();
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder binder) {
			s = ((WordService.MyBinder) binder).getService();
			Toast.makeText(ServiceConsumer.this, "Connected",
					Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName className) {
			s = null;
		}
	};

	void doBindService() {
		bindService(new Intent(this, WordService.class), mConnection,
				Context.BIND_AUTO_CREATE);
	}

	public void showServiceData(View view) {
		Toast.makeText(this, s.getWordList().get(0), Toast.LENGTH_SHORT).show();
	}
}