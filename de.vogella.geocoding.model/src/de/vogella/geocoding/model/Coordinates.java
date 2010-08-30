package de.vogella.geocoding.model;

public class Coordinates {
	private String latitude = "52.516074"; // Breitengrad
	private String longitude = "13.376987"; // Längengrad

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param langitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Latitude: " + latitude + " Longitude: " + longitude;
	}

}
