package de.vogella.webservice.java6.first.consumer;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

public class TestWS {
	public static void main(String[] args) {
		// Settting up the server connection
		WiseQuoteServerService service = new WiseQuoteServerService();
		WiseQuoteServer servicePort = service.getWiseQuoteServerPort();
		// Calling the webservice
		System.out.println(servicePort.getQuote("fun"));
		System.out.println(servicePort.getQuote("work"));

		// Alternatively if you want to specific the URL directly
		try {
			URL url = new URL("http://localhost:9191/wisequotes?wsdl");
			WiseQuoteServerService serviceWithUrl = new WiseQuoteServerService(
					url,
					new QName(
							"http://provider.first.java6.webservice.vogella.de/",
							"WiseQuoteServerService"));
			WiseQuoteServer servicePortWithUrl = serviceWithUrl
					.getWiseQuoteServerPort();
			System.out.println(servicePortWithUrl.getQuote("fun"));
			System.out.println(servicePortWithUrl.getQuote("work"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}
