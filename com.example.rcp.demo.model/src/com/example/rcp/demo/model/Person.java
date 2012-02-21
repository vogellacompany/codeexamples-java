package com.example.rcp.demo.model;

public class Person {
	private String firstName;
	private String lastName;
	private boolean married;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		married = false;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
