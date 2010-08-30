package de.vogella.task.model;

import de.vogella.task.model.intern.Task;

/**
 * Factory for creating Tasks
 * 
 * @author Lars Vogel
 * 
 */
public class Factory {
	private static long current = 10;

	// avoid instantiation of the factory
	private Factory() {

	}

	public synchronized static ITask createRequirementInstance() {
		return new Task(current++);
	}
}
