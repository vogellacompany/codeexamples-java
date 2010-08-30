package de.vogella.osgi.url.consumer;

import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(CommandProvider.class.getName(), new EquinoxShortenCommand(context), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

}
