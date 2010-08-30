package de.vogella.pagerank.crawler.links;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.vogella.web.crawler.model.Link;

public class LinksUtil {
	private static final Pattern fileNamePattern = Pattern.compile("/[^/]*\\.html$");
	private static final Pattern fragmentPattern = Pattern.compile("#");
	
	public static Link createAbsoluteLink(Link link) {
		String baseUrl = link.getUrl();
		String linkText = link.getLink();
	
		linkText = linkText.replaceAll("^/*", "");
		baseUrl = baseUrl.replaceAll("/*$", "");
	
		// Delete the "Fragment" from the link
		Matcher fragmentMatcher = fragmentPattern.matcher(linkText);
		while(fragmentMatcher.find()){
			int start = fragmentMatcher.start();
			linkText = linkText.substring(0,start);
		}
		
		// If the baseUrl is a webpage strip of the file name
		Matcher fileMatcher = fileNamePattern.matcher(baseUrl);
		while (fileMatcher.find()) {
			int start = fileMatcher.start();
			baseUrl = baseUrl.substring(0, start);
		}
			
//		if (.matches(pattern)){
//			baseUrl = baseUrl.replaceFirst(pattern, "");
//		}
			
		// Remove leading whitespace
		if (linkText.matches("\\s+.*")) {
			linkText = linkText.replaceFirst("^\\s+", "");
		}
		
		// We have to add the url
		if (!linkText.matches("(http|https)://.*")) {
			linkText = baseUrl + "/" + linkText;
		}
		
		
		return new Link(link.getUrl(), linkText);
	}

	
	public static boolean isValid(String link){
		URL url;
		try {
			url = new URL(link);
			return (LinkChecker.checkValid(url) );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return false; 	
	}


	
		
}
