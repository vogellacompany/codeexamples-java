package de.vogella.geocoding.model;

public class Address {

	private String street;
	private String number;
	private String postalCode;
	private String city;
	private String country;

	public Address() {
	}

	public Address(String postalCode, String city, String country) {
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		String s = "";
		s += street != null ? street + " " : "";
		s += number != null ? number + " " : "";
		s += postalCode != null ? postalCode + " " : "";
		s += city != null ? city + " " : "";
		s += country != null ? country + " " : "";

		return s;
	}

}