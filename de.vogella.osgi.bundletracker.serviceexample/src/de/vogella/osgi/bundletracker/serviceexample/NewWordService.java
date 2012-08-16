package de.vogella.osgi.bundletracker.serviceexample;

import de.vogella.osgi.annotations.OSGiService;
import de.vogella.osgi.quote.IQuoteService;



public class NewWordService implements IQuoteService {
	@OSGiService(name="someName",  value = "de.vogella.osgi.quote.IQuoteService")
	@Override
	public String getQuote() {
		return "Testen rocks!";
	}


}
