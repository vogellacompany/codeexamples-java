package de.vogella.rcp.net.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.net.proxy.view";
	private final ServiceTracker proxyTracker;

	public View() {
		proxyTracker = new ServiceTracker(FrameworkUtil.getBundle(
				this.getClass()).getBundleContext(), IProxyService.class
				.getName(), null);
		proxyTracker.open();
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		StyledText text = new StyledText(parent, SWT.NONE);
		text.setText(readWebpage());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

	private String readWebpage() {
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();

		try {
			URI uri = new URI("file:///www.vogella.de");
			IProxyService proxyService = getProxyService();
			IProxyData[] proxyDataForHost = proxyService.select(uri);

			for (IProxyData data : proxyDataForHost) {
				if (data.getHost() != null) {
					System.setProperty("http.proxySet", "true");
					System.setProperty("http.proxyHost", data.getHost());
				}
				if (data.getHost() != null) {
					System.setProperty("http.proxyPort", String.valueOf(data
							.getPort()));
				}
			}
			// Close the service and close the service tracker
			proxyService = null;

			URL url;

			url = uri.toURL();

			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				// Process each line.
				sb.append(inputLine + "\n");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return sb.toString();

	}

	public IProxyService getProxyService() {
		return (IProxyService) proxyTracker.getService();
	}

	@Override
	public void dispose() {
		proxyTracker.close();
		super.dispose();
	}

}