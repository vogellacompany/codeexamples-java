package de.vogella.osgi.bundletracker;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.util.tracker.BundleTracker;

import de.vogella.osgi.annotations.OSGiService;

public class ExtenderBundleTracker extends BundleTracker {

	public ExtenderBundleTracker(BundleContext context) {
		super(context, Bundle.ACTIVE, null);
	}

	@Override
	public Object addingBundle(Bundle bundle, BundleEvent event) {
		System.out.println("New bundle:" + bundle.getBundleId());
		String className = (String) bundle.getHeaders().get(
				"Service-Contribution");
		if (className != null) {
			System.out.println("Start scanning the class");
			Class<?> clazz;
			try {
				clazz = bundle.loadClass(className);
				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					OSGiService annotation = method
							.getAnnotation(OSGiService.class);
					System.out.println("name: " + annotation.name());
					System.out.println("value: " + annotation.value());
					bundle.getBundleContext().registerService(
							annotation.value(), clazz.newInstance(), null);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return bundle;
	}

	// Currently not used
	private void createService(Bundle bundle, String className) {
		Class<?> clazz;
		try {
			clazz = bundle.loadClass(className);
			bundle.getBundleContext().registerService(
					"de.vogella.osgi.quote.IQuoteService", clazz.newInstance(),
					null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}