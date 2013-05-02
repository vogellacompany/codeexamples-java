package de.vogella.android.listactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MySimpleArrayAdapter(Context context, String[] values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	static class Holger {
		static TextView text;
		static ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.rowlayout, parent, false);
			Holger holder = new Holger();
			rowView.setTag(holder);
			TextView textView = (TextView) rowView.findViewById(R.id.label);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			// Change the icon for Windows and iPhone
			holder.text = textView;
			holder.image = imageView;
		}
		Holger tag = (Holger) rowView.getTag();

		String s = values[position];
		tag.text.setText(s);
		if (s.startsWith("iPhone")) {
			tag.image.setImageResource(R.drawable.no);
		} else {
			tag.image.setImageResource(R.drawable.ok);
		}
		return rowView;
	}
}
