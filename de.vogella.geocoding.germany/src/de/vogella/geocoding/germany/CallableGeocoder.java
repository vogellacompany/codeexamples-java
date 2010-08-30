package de.vogella.geocoding.germany;
import java.util.concurrent.Callable;

import de.vogella.geocoding.model.Address;
import de.vogella.geocoding.model.Coordinates;
import de.vogella.geocoding.yahoo.Geocoder;

public class CallableGeocoder implements Callable<Coordinates> {
	private final Address address;

	public CallableGeocoder(Address address) {
		this.address = address;
	}

	@Override
	public Coordinates call() throws Exception {
		Geocoder geocoder = new Geocoder();
		geocoder
				.setApplicationId("nw6Ay_nV34Fvou19P_IxiOOsyjmw8F8vpUsT9BsyVcjJTDZYcA4uSblX7UnsGWzVSvZmjHbMsws");
		return geocoder.geocode(address);
	}

}
