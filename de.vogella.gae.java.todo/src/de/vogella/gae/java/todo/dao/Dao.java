package de.vogella.gae.java.todo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.datanucleus.sco.simple.GregorianCalendar;

import de.vogella.gae.java.todo.model.Todo;

public enum Dao {
	INSTANCE;
	private static long number = 1;

	private final List<Todo> todos = new ArrayList<Todo>();

	public List<Todo> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// Read the existing entries
		Query q = em.createQuery("select m from Todo todo");
		em.getTransaction().commit();

		// Create a test Todo
		// Image that I read the data from bigtables
		Todo todo = new Todo(1, "vogella", "Issue number 1",
				"Detailed Description of everything", "",
				GregorianCalendar.getInstance());
		todos.add(todo);
	}

	public void add(String author, String summery, String description,
			String url, Calendar dueDate) {
		synchronized (this) {
			number++;
			todos.add(new Todo(number, author, summery, description, url,
					dueDate));
		}
	}

	public List<Todo> getTodos(@SuppressWarnings("unused") String user) {
		// For testing we will always return the same data no matter what the
		// user is
		return todos;
	}

	public void remove(long id) {
		Todo remove = null;
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				remove = todo;
			}
		}
		if (remove != null) {
			todos.remove(remove);
		}

	}

}
