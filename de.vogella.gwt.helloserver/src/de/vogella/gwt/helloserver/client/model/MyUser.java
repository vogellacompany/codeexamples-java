package de.vogella.gwt.helloserver.client.model;

import java.io.Serializable;

public class MyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private String numberOfHits;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the numberOfHits
	 */
	public String getNumberOfHits() {
		return numberOfHits;
	}

	/**
	 * @param numberOfHits
	 *            the numberOfHits to set
	 */
	public void setNumberOfHits(String numberOfHits) {
		this.numberOfHits = numberOfHits;
	}

	//	
}
