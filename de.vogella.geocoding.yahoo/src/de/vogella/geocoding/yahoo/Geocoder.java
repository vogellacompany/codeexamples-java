package de.vogella.geocoding.yahoo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.vogella.geocoding.model.Address;
import de.vogella.geocoding.model.Coordinates;
import de.vogella.geocoding.yahoo.xml.YahooXmlReader;

public class Geocoder {
	private String applicationId;
	private final static String YAHOOURL = "http://local.yahooapis.com/MapsService/V1/geocode";

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Coordinates geocode(Address address) {
		Coordinates geocoordinates = null;
		String web = YAHOOURL + "?appid=" + applicationId + "&location="
				+ createLocation(address);
		URL url;
		try {
			url = new URL(web);
			// BufferedReader in = new BufferedReader(new );
			InputStream in = url.openStream();
			geocoordinates = YahooXmlReader.readConfig(in);
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return geocoordinates;
	}

	private String createLocation(Address address) {
		String s = "";

		s += address.getStreet() != null ? address.getStreet() + "+" : "";
		s = address.getCity() != null ? address.getCity() + "+" : "";
		s += address.getPostalCode() != null ? address.getPostalCode() + "+"
				: "";
		s += address.getCountry() != null ? address.getCountry() + "+" : "";
		if (s.endsWith("+")) {
			s = s.substring(0, s.length() - 1);
		}
		s = s.replace(" ", "+");
		return s;
	}
}
