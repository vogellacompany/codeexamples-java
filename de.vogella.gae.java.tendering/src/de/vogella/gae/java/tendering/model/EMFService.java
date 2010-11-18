package de.vogella.gae.java.tendering.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFService {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	private EMFService() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}

