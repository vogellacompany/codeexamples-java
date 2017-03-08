package com.vogella.java.retrofitgerrit;

import com.google.gson.annotations.SerializedName;

public class Change {
	
	@SerializedName("change_id")
	private String changeId;
	
	private String subject;
	
	@SerializedName("current_revision")
	private String currentRevisionId;
	
	private String project;
	
	private Account owner;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCurrentRevision() {
		return currentRevisionId == null ? "" : currentRevisionId;
	}

	public void setCurrentRevision(String currentRevision) {
		this.currentRevisionId = currentRevision;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}	
}
