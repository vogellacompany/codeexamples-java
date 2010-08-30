package de.vogella.web.crawler.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Webpage {

	private String url;
	private String title;
	private String description;
	private boolean crawled; 
	public boolean isCrawled() {
		return crawled;
	}

	public void setCrawled(boolean crawled) {
		this.crawled = crawled;
	}

	private Calendar lastTimeCrawled = null; 
	private List<Link> outboundLinks = new ArrayList<Link>();
	private List<Link> inboundLinks = new ArrayList<Link>();
	
	public Webpage(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Calendar getLastTimeCrawled() {
		return lastTimeCrawled;
	}
	public void setLastTimeCrawled(Calendar lastTimeCrawled) {
		this.lastTimeCrawled = lastTimeCrawled;
	}
	
	// Create defensive copy 
	public List<Link> getOutboundLinks() {
		return outboundLinks;
	}
	// Create defensive copy 
	public List<Link> getInboundLinks() {
		return  inboundLinks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Webpage other = (Webpage) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Webpage [url=" + url + "]";
	}
	
	
	
}
