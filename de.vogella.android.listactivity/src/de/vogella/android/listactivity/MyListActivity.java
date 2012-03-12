package de.vogella.android.listactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyListActivity extends ListActivity {
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		// String[] values = new String[] { "Android", "iPhone",
		// "WindowsMobile",
		// "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		// "Linux", "OS/2" };
		List<Person> list = createModel();
		ArrayAdapter<Person> adapter = new MySimplePersonAdapter(this, list);
		setListAdapter(adapter);
	}

	private List<Person> createModel() {
		ArrayList<Person> list = new ArrayList<Person>();
		Person person = new Person("Peter", "Parker");
		list.add(person);
		person = new Person("Jim", "Parker");
		list.add(person);
		person = new Person("Kim", "Parker");
		list.add(person);
		person = new Person("Bim", "Parker");
		list.add(person);
		return list;
	}
}