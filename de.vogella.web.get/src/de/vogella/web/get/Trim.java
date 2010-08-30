package de.vogella.web.get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Trim {
	private static final String trimUrl = "http://api.tr.im/v1/trim_simple?url=";
	
	public String shorter(String url) throws IOException {
		String tinyUrlLookup = trimUrl + url;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
		String tinyUrl = reader.readLine();
		return tinyUrl;
	}
}

