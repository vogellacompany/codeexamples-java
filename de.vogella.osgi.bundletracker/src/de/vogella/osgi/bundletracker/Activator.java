package de.vogella.osgi.bundletracker;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ExtenderBundleTracker extenderBundleTracker;


	public void start(BundleContext context) throws Exception {
		extenderBundleTracker = new ExtenderBundleTracker(context);
        extenderBundleTracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		extenderBundleTracker.close();
	}

}
