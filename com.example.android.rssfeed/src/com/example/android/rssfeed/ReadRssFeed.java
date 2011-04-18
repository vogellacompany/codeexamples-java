package com.example.android.rssfeed;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

public class ReadRssFeed extends Activity {
	static final String PUB_DATE = "pubDate";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LINK = "link";
	static final String TITLE = "title";
	static final String ITEM = "item";
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
	}

	public void readRss(View view) {
		if (parseTask != null) {
			parseTask = new ParseTask(this);
			parseTask
					.execute(new String[] { "http://www.vogella.de/article.rss" });
		}
	}

	public List<RssItem> parse(String rssFeed) {
		List<RssItem> list = new ArrayList<RssItem>();
		XmlPullParser parser = Xml.newPullParser();
		try {
			// auto-detect the encoding from the stream
			parser.setInput(new URL(rssFeed).openConnection().getInputStream(),
					null);
			int eventType = parser.getEventType();
			boolean done = false;
			RssItem item = null;
			while (eventType != XmlPullParser.END_DOCUMENT && !done) {
				String name = null;
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if (name.equalsIgnoreCase(ITEM)) {
						Log.i("new item", "Create new item");
						item = new RssItem();
					} else if (item != null) {
						if (name.equalsIgnoreCase(LINK)) {
							Log.i("Attribute", "setLink");
							item.setLink(parser.nextText());
						} else if (name.equalsIgnoreCase(DESCRIPTION)) {
							Log.i("Attribute", "description");
							item.setDescription(parser.nextText());
						} else if (name.equalsIgnoreCase(PUB_DATE)) {
							Log.i("Attribute", "date");
							item.setPubDate(parser.nextText());
						} else if (name.equalsIgnoreCase(TITLE)) {
							Log.i("Attribute", "title");
							item.setTitle(parser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					name = parser.getName();
					Log.i("End tag", name);
					if (name.equalsIgnoreCase(ITEM) && item != null) {
						Log.i("Added", item.toString());
						list.add(item);
					} else if (name.equalsIgnoreCase(CHANNEL)) {
						done = true;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Log.e("Elements", String.valueOf(list.size()));
		return list;

	}

	private static class ParseTask extends
			AsyncTask<String, Void, List<RssItem>> {
		private ReadRssFeed activity;

		private ParseTask(ReadRssFeed activity) {
			setActivity(activity);
		}

		public void setActivity(ReadRssFeed activity) {
			this.activity = activity;
			Log.d("Task", "ParseTask's activity: " + activity);
		}

		@Override
		protected List<RssItem> doInBackground(String... params) {
			return activity.parse("http://www.vogella.de/article.rss");
		}

		@Override
		protected void onPostExecute(List<RssItem> result) {
			finishWithText(String.valueOf(result.size()));
		}

		private void finishWithText(String text) {
			Log.d("Task", "ParseTask done. Updating activity: " + activity
					+ ", message: " + text);
			if (activity != null) {
				activity.parseTask = null;

				TextView textView = (TextView) activity
						.findViewById(R.id.textViewCount);
				textView.setText(text);
			}
		}
	}
}