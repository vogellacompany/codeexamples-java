package de.vogella.android.listactivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] names;

	public MyArrayAdapter(Activity context, String[] names) {
		super(context, R.layout.rowlayout, names);
		this.context = context;
		this.names = names;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.rowlayout, null, true);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		String s = names[position];
		textView.setText(s);
		if (s.startsWith("Windows7") || s.startsWith("iPhone")
				|| s.startsWith("Solaris")) {

			imageView.setImageResource(R.drawable.no);
		} else {
			imageView.setImageResource(R.drawable.ok);
		}

		return rowView;
	}
}
