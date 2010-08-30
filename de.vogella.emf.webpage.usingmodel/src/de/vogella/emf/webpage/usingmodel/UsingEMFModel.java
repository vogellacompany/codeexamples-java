package de.vogella.emf.webpage.usingmodel;

import de.vogella.emf.webpage.model.webpage.MyWeb;
import de.vogella.emf.webpage.model.webpage.Webpage;
import de.vogella.emf.webpage.model.webpage.WebpageFactory;
import de.vogella.emf.webpage.model.webpage.impl.WebpagePackageImpl;

public class UsingEMFModel {
	public static void main(String[] args) {
		WebpagePackageImpl.init();
		// Retrieve the default factory singleton
		WebpageFactory factory = WebpageFactory .eINSTANCE;
		// Create an instance of myWeb
		MyWeb myWeb = factory.createMyWeb();
		myWeb.setName("Hallo");
		myWeb.setDescription("This is a description");
		// Create a page
		Webpage webpage = factory.createWebpage();
		webpage.setTitle("This is a title");
		// Add the page to myWeb
		myWeb.getPages().add(webpage);
		// and so on, and so on
		// as you can see the EMF model can be (more or less) used as standard Java


	}
}
