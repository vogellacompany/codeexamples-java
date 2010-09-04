package de.vogella.android.listactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MyList extends ListActivity {

	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Create an array of Strings, that will be put to our ListActivity
		String[] names = new String[] { "Linux", "Windows7", "Eclipse", "Suse", "Ubuntu", "Solaris", "Android", "iPhone"};
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		this.setListAdapter(new MyArrayAdapter(this, names));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();
		Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
				.show();

	}
	
	
}