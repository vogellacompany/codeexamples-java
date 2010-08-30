
package de.vogella.groovy.first

public class Person{
	String firstName
	String lastName
	int age
	def address
	
	static void main(def args) {
		Person p = new Person()
		// Use the generated access methods
		p.setFirstName("Lars")
		// This will still use the generated access method, it is not a direct access!
		p.lastName = "Vogel" 
		p.address = ("Homestreet 3");
		println(p.firstName + " " + p.lastName);
		// Use the generated constructor
		p = new Person(firstName: "Peter", lastName:"Mueller");
		println(p.firstName + " " + p.lastName);
	}
	
}
