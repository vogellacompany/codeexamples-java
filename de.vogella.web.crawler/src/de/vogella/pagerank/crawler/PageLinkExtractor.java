package de.vogella.pagerank.crawler;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.vogella.web.crawler.model.Link;
import de.vogella.web.crawler.model.Webpage;

public class PageLinkExtractor {
	private Pattern htmltag = Pattern
			.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
	private Pattern link = Pattern.compile("href=\"[^\"]*");
	
	public Set<Link> extractAllLinks(String webpageContent, Webpage page) {
		Set<Link> links = new HashSet<Link>();
			Matcher tagmatch = htmltag.matcher(webpageContent);
			while (tagmatch.find()) {
				Matcher matcher = link.matcher(tagmatch.group());
				matcher.find();
				String linkText = matcher.group().replaceFirst("href=\"", "")
						.replaceFirst("\"", "");
				if (valid(linkText)){
					links.add(new Link(page.getUrl(), linkText));
				}
			
			}
		
		return links;
	}
	
	/**
	 * Link is not javascript or mailto
	 **/

	private boolean valid(String s) {
		if (s.matches("javascript:.*|mailto:.*|#.*")) {
			return false;
		}
		return true;
	}

	
}
