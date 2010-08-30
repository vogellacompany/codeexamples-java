package de.vogella.web.crawler.model;

/**
 * Link is immutable
 * @author Lars Vogel
 *
 */
public class Link {
	
	private final String url;
	private final String link;
	
	public Link(String webpage, String linkText) {
		this.url = webpage;
		link = linkText;
	}

	public String getUrl() {
		return url;
	}

	public String getLink() {
		return link;
	}
	
	@Override
	public String toString() {
		return link;
	}
	
	
}
