package de.vogella.osgi.quoteservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.vogella.osgi.quote.IQuoteService;
import de.vogella.osgi.quoteservice.internal.QuoteService;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting quoteservice bundles");
		IQuoteService service = new QuoteService();
		// Third parameter is a hashmap which allows to configure the service
		// Not required in this example
		context.registerService(IQuoteService.class.getName(), service,
				null);
		System.out.println("IQuoteService is registered");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping quoteservice bundles");
	}

}
