package de.vogella.pde.dependencies.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

public class PrintDependencies extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String requireBundle = (String)Platform.getBundle("de.vogella.pde.dependencies").getHeaders().get(
				Constants.REQUIRE_BUNDLE);
				try {
					ManifestElement[] elements = ManifestElement.parseHeader(
					Constants.BUNDLE_CLASSPATH, requireBundle);
					for (ManifestElement manifestElement : elements) {
						System.out.println( manifestElement.getValue());
					}
				} catch (BundleException e) {
					e.printStackTrace();
				}
		return null;
	}
}
