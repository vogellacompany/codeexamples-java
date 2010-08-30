package de.vogella.osgi.url.tinyurl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.vogella.osgi.url.shorten.IShorten;

public class Activator implements BundleActivator {
	
	public void start(BundleContext context) throws Exception {
		context.registerService(IShorten.class.getName(), new TinyURL(), null);
	}

	public void stop(BundleContext context) throws Exception {
	}

}
