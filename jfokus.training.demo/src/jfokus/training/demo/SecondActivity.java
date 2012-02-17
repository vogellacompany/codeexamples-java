package jfokus.training.demo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SecondActivity extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylistview);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		getListView().setBackgroundResource(R.drawable.fire);
	}
}
