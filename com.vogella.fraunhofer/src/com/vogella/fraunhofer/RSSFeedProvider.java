package com.vogella.fraunhofer;

import java.util.ArrayList;
import java.util.List;

public class RSSFeedProvider {
	public static List<RssItem> parse(String rssFeed) {
		List<RssItem> list = new ArrayList<RssItem>();
		RssItem item = new RssItem();
		item.setTitle("Test1");
		list.add(item);
		item = new RssItem();
		item.setTitle("Test2");
		list.add(item);
		item.setTitle("Test3");
		list.add(item);
		return list;
	}
}
