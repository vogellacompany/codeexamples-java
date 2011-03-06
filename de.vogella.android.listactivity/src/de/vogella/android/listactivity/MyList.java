package de.vogella.android.listactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyList extends ListActivity {

	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Create an array of Strings, that will be put to our ListActivity
		ArrayAdapter<Model> adapter = new InteractiveArrayAdapter(this,
				getModel());
		setListAdapter(adapter);
	}

	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		list.add(get("Linux"));
		list.add(get("Windows7"));
		list.add(get("Suse"));
		list.add(get("Eclipse"));
		list.add(get("Ubuntu"));
		list.add(get("Solaris"));
		list.add(get("Android"));
		list.add(get("iPhone"));
		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}

	private Model get(String s) {
		return new Model(s);
	}

}