package de.vogella.databinding.example;

public class Person extends ModelObject {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		firePropertyChange("name", this.name, this.name = name);
	}
}