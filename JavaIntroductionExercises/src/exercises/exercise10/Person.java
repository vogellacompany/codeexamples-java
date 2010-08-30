package exercises.exercise10;

public class Person {
	// We define two variables for the first and last name
	// String is the type of the variable
	private String firstName;
	private String lastName;

	// This is the constructor of the class it expects two variables:
	// firstName
	// lastName
	public Person(String firstName, String lastName) {
		// the keyword "this" allows to refer to variables of the class.
		// It allows to distinguish between the variables which are passed
		// into the constructor and the variables of the class
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String s) {
		firstName = s;
	}

	public void setLastName(String s) {
		lastName = s;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
