package com.example.aaaa.testing;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyListActivity extends ListActivity {

	private String[] values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		values = new String[] { "Android", "iPhone", "Nokia", "iPhone",
				"Android", "iPhone", "Windows", "iPhone", "Ubutu", "iPhone",
				"Android", "iPhone" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, values[position], Toast.LENGTH_SHORT).show();
	}
}
