package de.vogella.gae.java.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String author;

	private String summary;
	private String description;
	private String url;
	boolean finished;

	public Todo(String author, String summary, String description,
			String url) {
		this.author = author;
		this.summary = summary;
		this.description = description;
		this.url = url;
		finished = false;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getShortDescription() {
		return summary;
	}

	public void setShortDescription(String shortDescription) {
		this.summary = shortDescription;
	}

	public String getLongDescription() {
		return description;
	}

	public void setLongDescription(String longDescription) {
		this.description = longDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
