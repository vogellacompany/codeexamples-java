package com.vogella.java.dagger2;

import javax.inject.Inject;

public class User {
	
	private String firstName;
	private String lastName;
	
	@Inject
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
