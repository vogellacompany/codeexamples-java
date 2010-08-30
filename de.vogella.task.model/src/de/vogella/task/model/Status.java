package de.vogella.task.model;

public enum Status {
	NEW("New"), STARTED("Started"), DONE("Done");
	String name;

	private Status(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
