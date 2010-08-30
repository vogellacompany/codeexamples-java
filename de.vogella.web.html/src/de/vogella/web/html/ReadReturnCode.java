package de.vogella.web.html;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadReturnCode {
	public static void main(String[] args) throws IOException {
		String urltext = "http://www.vogella.de";
		URL url = new URL(urltext);
		int responseCode = ((HttpURLConnection) url.openConnection())
				.getResponseCode();
		System.out.println(responseCode);
	}
}
