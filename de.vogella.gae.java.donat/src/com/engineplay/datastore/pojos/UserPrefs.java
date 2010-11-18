package com.engineplay.datastore.pojos;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import com.google.appengine.api.users.User;

@Entity(name = "UserPrefs")
public class UserPrefs {
	@Id
	private String userId;
	private int donuts;
	@Basic
	private User user;

	public UserPrefs(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getDonuts() {
		return donuts;
	}

	public void setDonuts(int donuts) {
		this.donuts = donuts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static UserPrefs getPrefsForUser(User user) {
		UserPrefs userPrefs = null;
		EntityManager em = EMFService.get().createEntityManager();
		try {
			if (em.find(UserPrefs.class, user.getEmail()) == null) {

				userPrefs = new UserPrefs(user.getEmail());
				userPrefs.setUser(user);

			} else {
				userPrefs = em.find(UserPrefs.class, user.getEmail());
			}
		} finally {
			em.close();
		}
		return userPrefs;
	}

	public void save() {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.persist(this);
		} finally {
			em.close();
		}
	}

	public void remove() {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			UserPrefs userPrefs = em.find(UserPrefs.class, user.getEmail());
			em.remove(userPrefs);
		} finally {
			em.close();
		}
	}
}
