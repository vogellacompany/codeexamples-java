package android.jfocus.receiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ActivityTest extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		HashMap<String, String> items = new HashMap<String, String>();
		items.put("name", "Android");
		items.put("purpose", "Mobile");
		list.add(items);
		items = new HashMap<String, String>();
		items.put("name", "Windows7");
		items.put("purpose", "Desktop");

		list.add(items);
		items = new HashMap<String, String>();
		items.put("name", "iPhone");
		items.put("purpose", "Mobile");
		list.add(items);
		String[] from = { "name", "purpose" };
		int[] to = { android.R.id.text1, android.R.id.text2 };

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, from, to);
		setListAdapter(adapter);
	}
}