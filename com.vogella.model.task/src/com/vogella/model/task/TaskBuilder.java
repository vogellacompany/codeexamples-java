package com.vogella.model.task;

import java.util.Date;

public class TaskBuilder {
	private final long id;
	private String summary = "";
	private String description = "";
	private boolean done = false;
	private Date dueDate;

	public  TaskBuilder(long id) {
		this.id = id;
	}
	
	public TaskBuilder(long id, String summary, String description, boolean done,
			Date dueDate) {
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.done = done;
		this.dueDate = dueDate;
	}

	public TaskBuilder setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public TaskBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public TaskBuilder setDone(boolean done) {
		this.done = done;
		return this;
	}

	public TaskBuilder setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		return this;
	}
	public Task build() {
		return new Task(id,summary, description,done, dueDate);
	}
}
