package de.vogella.gae.java.c2dm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.vogella.gae.java.c2dm.model.RegisteredDevice;

public enum Dao {
	INSTANCE;

	public List<RegisteredDevice> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from RegisteredDevice m");
		List<RegisteredDevice> todos = q.getResultList();
		return todos;
	}

	public void add(String deviceId, String registrationId) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			RegisteredDevice todo = new RegisteredDevice(deviceId,
					registrationId);
			em.persist(todo);
			em.close();
		}
	}

	public List<RegisteredDevice> getDevices() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from RegisteredDevice t");
		@SuppressWarnings("unchecked")
		List<RegisteredDevice> todos = q.getResultList();
		return todos;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			RegisteredDevice todo = em.find(RegisteredDevice.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}
}
