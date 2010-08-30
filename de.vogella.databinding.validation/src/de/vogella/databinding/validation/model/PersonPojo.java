package de.vogella.databinding.validation.model;

public class PersonPojo {
	private String firstName;
	private String lastName;
	private boolean married;
	private String gender;
	private Integer age;

	public PersonPojo() {
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
		this.firstName = firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMarried(boolean isMarried) {
		this.married = isMarried;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}