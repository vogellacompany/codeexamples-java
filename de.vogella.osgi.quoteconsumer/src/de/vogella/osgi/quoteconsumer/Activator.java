package de.vogella.osgi.quoteconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import de.vogella.osgi.quote.IQuoteService;

public class Activator implements BundleActivator {

	private ServiceTracker serviceTracker;

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting quoteconsumer bundles");
		// Register directly with the service
		MyQuoteServiceTrackerCustomizer customer = new MyQuoteServiceTrackerCustomizer(
				context);
		serviceTracker = new ServiceTracker(context, IQuoteService.class
				.getName(), customer);
		serviceTracker.open();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping quoteconsumer bundles");
		serviceTracker.close();
	}

}
