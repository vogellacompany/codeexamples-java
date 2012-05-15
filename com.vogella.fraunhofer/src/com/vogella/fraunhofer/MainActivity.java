package com.vogella.fraunhofer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button buttonView;
	int zahl = 10;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		buttonView = (Button) findViewById(R.id.button1);

	}

	public void onClick(View view) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					// Datenbank update simulation
					Thread.sleep(5000);
					zahl = 10;
					buttonView.setText("Halllooooo");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

	}
}