package com.vogella.java.retrofitxml;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="rss", strict=false)
public class VogellaRSSFeed {

	@Element(name="title")
	@Path("channel")
	private String channelTitle;

	@ElementList(name="item", inline=true)
	@Path("channel")
	private List<Article> articleList;

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
	
	
}
