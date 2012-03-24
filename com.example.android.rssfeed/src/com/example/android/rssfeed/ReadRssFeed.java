package com.example.android.rssfeed;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadRssFeed extends ListActivity {

	private ParseTask parseTask;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		// .detectAll().penaltyLog().penaltyDeath().build());
		// StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
		// .penaltyLog().penaltyDeath().build());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		parseTask = (ParseTask) getLastNonConfigurationInstance();
		if (parseTask != null) {
			parseTask.setActivity(this);
		}
		ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this,
				R.layout.rowlayout, android.R.id.text1, RssApplication.list);
		RssApplication application = (RssApplication) getApplication();
		setListAdapter(adapter);
	}

	public void parseRss(View view) {
		Log.e("DEBUG", "parseRss");
		if (parseTask == null) {
			parseTask = new ParseTask(this);
			parseTask
					.execute(new String[] { "http://www.vogella.de/article.rss" });
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
	}

	private static class ParseTask extends
			AsyncTask<String, Void, List<RssItem>> {
		private ReadRssFeed activity;

		private ParseTask(ReadRssFeed activity) {
			setActivity(activity);
		}

		public void setActivity(ReadRssFeed activity) {
			this.activity = activity;
			Log.d("DEBUG", "ParseTask's activity: " + activity);
		}

		@Override
		protected List<RssItem> doInBackground(String... params) {
			Log.d("DEBUG", "doInBackground");
			return RssFeedReader.parse(params[0], activity);
		}

		@Override
		protected void onPostExecute(List<RssItem> list) {
			Log.w("DEBUG", "onPostExecute called");
			finishWithText(list);
		}

		private void finishWithText(List<RssItem> list) {
			String text = String.valueOf(list.size());
			Log.d("DEBUG", "ParseTask done. Updating activity: " + activity
					+ ", message: " + text);
			if (activity != null) {
				activity.parseTask = null;

				TextView textView = (TextView) activity
						.findViewById(R.id.textViewCount);
				textView.setText(text);
				ArrayAdapter<RssItem> adapter = (ArrayAdapter<RssItem>) activity
						.getListAdapter();
				adapter.clear();
				adapter.addAll(list);
				RssApplication.list = list;
			}
		}
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		if (parseTask != null) {
			return parseTask;
		}
		return super.onRetainNonConfigurationInstance();
	}

	@Override
	protected void onDestroy() {
		if (parseTask != null) {
			parseTask.setActivity(null);
		}
		super.onDestroy();
	}
}