package com.vogella.javastarter.exercises1;


class Person {
	String firstName;
	String lastName;
	int age;
	private Address address;

	public Person(String a, String b, int value) {
		firstName = a;
		lastName = b;
		age=value;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
