package de.vogella.android.list.filter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MyFilteringListAdapter extends ArrayAdapter<String> {

	private final Context context;
	private List<String> values;
	private List<String> completeList;

	public MyFilteringListAdapter(Context context, List<String> values) {
		super(context, android.R.layout.simple_list_item_1, android.R.id.text1,
				values);
		this.context = context;
		this.values = values;
		completeList = new ArrayList<String>(values);
		System.out.println(completeList.size());
		System.out.println(values.size());
	}

	public void setFilter(String s) {
		String quoted = s.replaceAll("\\*", "\\.\\*");
		Log.e("Test", quoted);
		values.clear();
		if (s.length() == 0) {
			values.addAll(completeList);
		} else {
			for (int i = 0; i < completeList.size(); i++) {
				String value = completeList.get(i);
				if (value.toLowerCase().matches(quoted.toLowerCase() + ".*")) {
					values.add(value);
				}
			}
		}
		notifyDataSetChanged();
	}
}
