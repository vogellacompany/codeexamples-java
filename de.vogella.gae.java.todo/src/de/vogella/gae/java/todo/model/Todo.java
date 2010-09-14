package de.vogella.gae.java.todo.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Model class which will store the Todo Items
 * 
 * @author Lars Vogel
 *
 */
@Entity
public class Todo {
	@Id
	private final long id; 
	private String author;
	private String shortDescription;
	private String longDescription;
	private String url;
	private Calendar dueDate;
	boolean finished;
	
	public Todo(long id, String author, String shortDescription, String longDescription,  String url,  Calendar dueDate) {
		this.id= id; 
		this.author = author;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.url = url;
		this.dueDate = dueDate;
		finished = false; 
	}
	
	public long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Calendar getDueDate() {
		return dueDate;
	}
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	
}
