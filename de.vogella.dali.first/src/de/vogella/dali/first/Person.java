package de.vogella.dali.first;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Entity implementation class for Entity: Person
 *
 */
@Entity

@SequenceGenerator(name = "SEQUENCE")
public class Person implements Serializable {
	@Id
	private int id;
	private String firstName;
	private String lastName;
	
	private static final long serialVersionUID = 1L;

	public Person() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
   
}
