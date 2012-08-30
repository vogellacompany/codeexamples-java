package com.example.android.rssfeedlibary;

import java.util.ArrayList;
import java.util.List;

public class RssFeedProvider {
	// Helper method to get a list
	// of RssItems

	public static List<RssItem> parse(String rssFeed) {

		List<RssItem> list = new ArrayList<RssItem>();

		// Create some example data
		RssItem item = new RssItem("test1", "l1");
		list.add(item);
		item = new RssItem("test2", "l2");
		list.add(item);
		// TODO Create a few more instances of RssItem

		return list;
	}
}
