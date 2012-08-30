package com.example.android.rssfeed;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.rssfeedlibary.RssFeedProvider;
import com.example.android.rssfeedlibary.RssItem;

public class MyListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rsslist_overview,
				container, false);
		Button button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateDetail();
			}
		});
		return view;
	}

	private void updateDetail() {
		DetailFragment fragment = (DetailFragment) getFragmentManager()
				.findFragmentById(R.id.detailFragment);

		// Accessing the library project
		List<RssItem> list = RssFeedProvider
				.parse("http://www.vogella.com/article.rss");
		String numberOfItems = String.valueOf(list.size());
		if (fragment != null && fragment.isInLayout()) {
			fragment.setText(numberOfItems);
		} else {
			Intent intent = new Intent(getActivity().getApplicationContext(),
					DetailActivity.class);
			intent.putExtra("value", numberOfItems);
			startActivity(intent);

		}
	}
}