package de.vogella.osgi.ds.quoteconsumer;

import de.vogella.osgi.quote.IQuoteService;

public class QuoteConsumer {
	private IQuoteService service;

	public void quote() {
		System.out.println(service.getQuote());
	}

	// Method will be used by DS to set the quote service
	public synchronized void setQuote(IQuoteService service) {
		System.out.println("Service was set. Thank you DS!");
		this.service = service;
		// I know I should not use the service here but just for demonstration
		System.out.println(service.getQuote());
	}

	// Method will be used by DS to unset the quote service
	public synchronized void undsetQuote(IQuoteService service) {
		System.out.println("Service was unset. Why did you do this to me?");
		if (this.service == service) {
			this.service = null;
		}
	}
}
