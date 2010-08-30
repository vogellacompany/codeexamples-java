package de.vogella.osgi.url.tinyurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import de.vogella.osgi.url.shorten.IShorten;

public class TinyURL implements IShorten {
	private static final String startUrl = "http://tinyurl.com/api-create.php?url=";
	
	@Override
	public String makeShortURL(String url) throws IOException {
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "proxy");
		System.setProperty("http.proxyPort", "8080");

		String tinyUrlLookup = startUrl + url;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
		String tinyUrl = reader.readLine();
		return tinyUrl;
	}

}
