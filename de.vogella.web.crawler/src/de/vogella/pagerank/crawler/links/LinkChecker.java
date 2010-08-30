package de.vogella.pagerank.crawler.links;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * <p>
 * Must consider the mime-type of the resource, only text/html is a valid html
 * link
 * <p>
 * Must consider the return type of the webresource. Currently I'm only
 * accepting return code 200 as a correct code
 * 
 */
public class LinkChecker {
	public static boolean checkValid(URL url){
		return (valid(url)&& htmlResource(url));
	}
	
	private static boolean valid(URL url) {
		try {
			int responseCode = ((HttpURLConnection) url.openConnection())
					.getResponseCode();
			if (responseCode == 200) {
				return true;
			}
			if (responseCode == 404) {
				return false;
			}

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return false;
	}

	private static boolean htmlResource(URL url) {
		try {
			String contentType = ((HttpURLConnection) url.openConnection())
					.getContentType();
			if (contentType.startsWith("text/html")) {
				return true;
			}
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return false;

	}
}
