package de.vogella.gae.java.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.vogella.gae.java.todo.model.Todo;

public enum Dao {
	INSTANCE;

	public List<Todo> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Todo todo");
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void add(String author, String summery, String description,
			String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Todo todo = new Todo(author, summery, description, url);
			em.persist(todo);
			em.close();
		}
	}

	public List<Todo> getTodos(@SuppressWarnings("unused") String user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Todo t");
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Todo todo = em.find(Todo.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}
}
