package de.vogella.osgi.url.shorten;

import java.io.IOException;

public interface IShorten {
	String makeShortURL(String url) throws IOException;

}
