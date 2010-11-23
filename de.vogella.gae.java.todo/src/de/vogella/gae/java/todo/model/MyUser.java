package de.vogella.gae.java.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String userId;
	private String email;

	public MyUser(String userId, String email) {
		this.id = userId;
		this.userId = userId;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

}
