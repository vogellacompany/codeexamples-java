package de.vogella.groovy.java;

public class Main {
	public static void main(String[] args) {
		Person p = new Person();
		p.setFirstName("Lars");
		p.setLastName("Vogel");
		System.out.println(p.getFirstName() + " " + p.getLastName());
	}
}
