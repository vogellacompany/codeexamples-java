package de.vogella.android.listactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyListActivity extends ListActivity {
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.rowlayout, R.id.label, values);
		setListAdapter(adapter);
	}

}