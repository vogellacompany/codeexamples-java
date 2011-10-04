package de.vogella.databinding.example.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Address {

	private String street;
	private String number;
	private String postalCode;
	private String city;
	private String country;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

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
		propertyChangeSupport.firePropertyChange("street", this.street,
				this.street = street);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		propertyChangeSupport.firePropertyChange("number", this.number,
				this.number = number);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		propertyChangeSupport.firePropertyChange("postalCode", this.postalCode,
				this.postalCode = postalCode);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		propertyChangeSupport.firePropertyChange("citry", this.city,
				this.city = city);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		propertyChangeSupport.firePropertyChange("country", this.country,
				this.country = country);
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
