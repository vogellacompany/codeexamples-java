package de.vogella.web.html;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadMimeType {
	public static void main(String[] args) throws IOException {
		String urltext = "http://www.vogella.de";
		URL url = new URL(urltext);
		String contentType = ((HttpURLConnection) url.openConnection())
				.getContentType();
		System.out.println(contentType);
	}
}
