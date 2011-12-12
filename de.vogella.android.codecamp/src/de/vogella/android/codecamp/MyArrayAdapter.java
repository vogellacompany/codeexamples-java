package de.vogella.android.codecamp;

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

	// static to save the reference to the outer class and to avoid access to
	// any members of the containing class
	static class ViewHolder {
		public ImageView imageView;
		public TextView textView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ViewHolder will buffer the assess to the individual fields of the row
		// layout

		ViewHolder holder;
		// Recycle existing view if passed as parameter
		// This will save memory and time on Android
		// This only works if the base layout for all classes are the same
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.rowlayout, null, true);
			holder = new ViewHolder();
			holder.textView = (TextView) rowView.findViewById(R.id.label);
			holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}

		holder.textView.setText(names[position]);
		// Change the icon for Windows and iPhone
		String s = names[position];
		if (s.startsWith("Windows7") || s.startsWith("iPhone")
				|| s.startsWith("Solaris")) {

			holder.imageView.setImageResource(R.drawable.ic_launcher);
		} else {
			holder.imageView.setImageResource(R.drawable.adbreset);
		}

		return rowView;
	}
}
