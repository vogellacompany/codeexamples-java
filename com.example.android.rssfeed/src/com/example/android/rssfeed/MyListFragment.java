package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssItem;

public class MyListFragment extends ListFragment {
	ParseTask parseTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List<RssItem> list = new ArrayList<RssItem>();
		MyAdapter adapter = new MyAdapter(getActivity(),
				android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		setRetainInstance(true);
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
		private MyListFragment fragment;

		public synchronized void setFragment(MyListFragment fragment) {
			this.fragment = fragment;
		}

		@Override
		protected List<RssItem> doInBackground(String... params) {
			List<RssItem> list = RssFeedProvider.parse(params[0]);
			return list;
		}

		@Override
		protected void onPostExecute(List<RssItem> result) {
			fragment.setListContent(result);
		}
	}

	public void updateListContent() {
		if (parseTask == null) {
			parseTask = new ParseTask();
			parseTask.setFragment(this);
			parseTask.execute("http://www.vogella.com/article.rss");
		}
	}

	public void setListContent(List<RssItem> result) {
		ArrayAdapter listAdapter = (ArrayAdapter) getListAdapter();
		listAdapter.clear();
		listAdapter.addAll(result);
		parseTask.setFragment(null);
		parseTask = null;
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

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		// Get existing preference
		String string = prefs.getString("url", "kdsfkds");

	}
}