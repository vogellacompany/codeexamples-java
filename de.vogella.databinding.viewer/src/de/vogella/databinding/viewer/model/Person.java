package de.vogella.databinding.viewer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Person implements PropertyChangeListener {
	private String firstName;
	private String lastName;
	private boolean married;
	private String gender;
	private Integer age;
	private Address address;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Person() {
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isMarried() {
		return married;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName,
				this.firstName = firstName);
	}

	public void setGender(String gender) {
		propertyChangeSupport.firePropertyChange("gender", this.gender,
				this.gender = gender);
	}

	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName,
				this.lastName = lastName);
	}

	public void setMarried(boolean isMarried) {
		propertyChangeSupport.firePropertyChange("married", this.married,
				this.married = isMarried);
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		propertyChangeSupport.firePropertyChange("age", this.age,
				this.age = age);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		address.addPropertyChangeListener("country", this);
		propertyChangeSupport.firePropertyChange("address", this.address,
				this.address = address);
	}

	@Override
	public String toString() {
		return firstName;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		propertyChangeSupport.firePropertyChange("address", null, address);
	}

}