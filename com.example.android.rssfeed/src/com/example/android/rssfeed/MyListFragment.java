package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssItem;

public class MyListFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<RssItem> list = new ArrayList<RssItem>();

		ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(
				getActivity(), android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rsslist_overview,
				container, false);

		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		RssItem item = (RssItem) getListAdapter().getItem(position);
		updateDetail(item);
	}

	private static class ParseTask extends
			AsyncTask<String, Void, List<RssItem>> {
		private RssfeedActivity activity;

		@Override
		protected List<RssItem> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(List<RssItem> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}

	public void updateListContent() {
		// Reading the RSS items
		List<RssItem> list = RssFeedProvider
				.parse("http://www.vogella.com/article.rss");
		ArrayAdapter listAdapter = (ArrayAdapter) getListAdapter();
		listAdapter.clear();
		listAdapter.addAll(list);
	}

	public void updateDetail(RssItem item) {
		DetailFragment fragment = (DetailFragment) getFragmentManager()
				.findFragmentById(R.id.detailFragment);

		if (fragment != null && fragment.isInLayout()) {
			fragment.setText(item.getLink());
		} else {
			Intent intent = new Intent(getActivity().getApplicationContext(),
					DetailActivity.class);
			intent.putExtra("value", item.getLink());
			startActivity(intent);

		}
	}
}