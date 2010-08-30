package de.vogella.osgi.quoteconsumer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import de.vogella.osgi.quote.IQuoteService;

public class MyQuoteServiceTrackerCustomizer implements
		ServiceTrackerCustomizer {

	private final BundleContext context;

	public MyQuoteServiceTrackerCustomizer(BundleContext context) {
		this.context = context;
	}

	private MyThread thread;

	@Override
	public Object addingService(ServiceReference reference) {
		IQuoteService service = (IQuoteService) context.getService(reference);
		thread = new MyThread(service);
		thread.start();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		// removedService(reference, service);
		// addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("How sad. Service for quote is gone");
		thread.stopThread();
	}

	public static class MyThread extends Thread {

		private volatile boolean active = true;
		private final IQuoteService service;

		public MyThread(IQuoteService service) {
			this.service = service;
		}

		public void run() {
			while (active) {
				System.out.println(service.getQuote());
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					System.out.println("Thread interrupted " + e.getMessage());
				}
			}
		}

		public void stopThread() {
			active = false;
		}
	}

}
