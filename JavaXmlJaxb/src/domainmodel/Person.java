package domainmodel;



public class Person{


	private String firstName;
	private String lastName;
	private Family family;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Leave the standard column name of the table
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}


	
}