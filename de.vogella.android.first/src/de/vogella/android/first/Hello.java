				
package de.vogella.android.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Hello extends Activity {

	private EditText text;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // bind the layout to the activity
		text = (EditText) findViewById(R.id.EditText01);
		text.setText("No button pressed");

	}

	// Will be connected with the buttons via XML
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			text.setText("Button 1 was clicked");
			break;
		case R.id.Button02:
			text.setText("Button 2 was clicked");
			break;

		case R.id.Button03:
			text.setText("Button 3 was clicked");
			break;
		}
	}
}
