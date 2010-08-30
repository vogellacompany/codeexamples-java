package de.vogella.osgi.url.consumer;

import java.io.IOException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.vogella.osgi.url.shorten.IShorten;

public class ShortenCommand {

	private BundleContext context;

	public ShortenCommand (BundleContext context) {
		this.context = context; 
	}
	
	protected String shorten(String url) throws IllegalArgumentException, IOException{
		ServiceReference ref = context.getServiceReference(IShorten.class.getName());
		if (ref==null){
			return null;
		}
		IShorten shorten = (IShorten) context.getService(ref);
		if (shorten==null){
			return null;
		}
		return shorten.makeShortURL(url);
	}


}
