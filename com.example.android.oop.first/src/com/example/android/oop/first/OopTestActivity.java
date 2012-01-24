package com.example.android.oop.first;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class OopTestActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

	}
	// public void onClick(View view) {
	// EditText editText = (EditText) findViewById(R.id.message);
	// String string = editText.getText().toString();
	// Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	// // Intent intent = new Intent(this, SecondActivity.class);
	// // startActivity(intent);
	// }
}