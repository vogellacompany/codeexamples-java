package de.vogella.task.model;

/**
 * Contains the possible priorities of Tasks
 * 
 * @author Lars Vogel
 * 
 */
public enum Priority {
	LOW("Low", 0), MEDIUM("Medium", 1), HIGH("High", 2);
	private final String name;
	private final int index;

	private Priority(String name, int priority) {
		this.name = name;
		this.index = priority;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return name;
	}

}
