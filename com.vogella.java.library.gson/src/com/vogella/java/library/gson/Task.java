package com.vogella.java.library.gson;

public class Task {
	private final long id;
	private String summary;
	private String description;
	private Status status;
	private int priority;
		
	public enum Status {
		CREATED, ASSIGNED, CANCELED, COMPLETED 
	}
	

	
	
	public Task(long id, String summary, String description, Status status,
			int priority) {
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}

	public long getId() {
		return id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", summary=" + summary + ", description="
				+ description + ", status=" + status + ", priority=" + priority
				+ "]";
	}
	
	
	
}
