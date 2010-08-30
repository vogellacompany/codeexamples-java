package de.vogella.network.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadWebPage {
	public static void main(String[] args) throws IOException {
		String urltext = "http://www.vogella.de";
		URL url = new URL(urltext);
		BufferedReader in = new BufferedReader(new InputStreamReader(url
				.openStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			// Process each line.
			System.out.println(inputLine);
		}
		in.close();
	}
}
