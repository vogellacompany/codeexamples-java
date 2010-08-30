
package de.vogella.groovy.first



public class Person2{
	String firstName
	String lastName
	int age
	def address
	
	static void main(def args) {
		Person p =  new Person(firstName: "Peter", lastName:"Mueller");
		println(p?.firstName + " " + p?.lastName);
		
	}
	
}
