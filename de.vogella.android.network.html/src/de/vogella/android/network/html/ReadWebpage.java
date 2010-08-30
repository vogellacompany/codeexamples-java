package de.vogella.android.network.html;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ReadWebpage extends Activity {
	private static final String PREFERENCES = "PREFERENCES";
	private static final String URL = "url";
	private String lastUrl;
	private EditText urlText;
	private TextView textView;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		urlText = (EditText) findViewById(R.id.address);
		textView = (TextView) findViewById(R.id.pagetext);

		loadPreferences();
		urlText.setText(lastUrl);
	}

	
	/**
	 * Demonstrates loading of preferences The last value in the URL string will
	 * be loaded
	 */
	private void loadPreferences() {
		SharedPreferences preferences = getSharedPreferences(PREFERENCES,
				Activity.MODE_PRIVATE);
		// Set this to the Google Homepage
		lastUrl = preferences.getString(URL, "http://209.85.229.147");
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences preferences = getSharedPreferences(PREFERENCES,
				Activity.MODE_PRIVATE);
		Editor preferenceEditor = preferences.edit();
		preferenceEditor.putString(URL, urlText.getText().toString());
		// You have to commit otherwise the changes will not be remembered
		preferenceEditor.commit();
	}
	
	// Will be connected with the buttons via XML
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.ReadWebPage:
			try {
				textView.setText("");
				// Perform action on click
				URL url = new URL(urlText.getText().toString());
				// Get the response
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(url.openStream()));
				String line = "";
				while ((line = rd.readLine()) != null) {
					textView.append(line);
				}
			}

			catch (Exception e) {
				System.out.println("Nay, did not work");
				textView.setText(e.getMessage());
			}
			break;
		}
	}

}
