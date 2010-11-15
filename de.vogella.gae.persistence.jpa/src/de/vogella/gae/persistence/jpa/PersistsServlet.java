package de.vogella.gae.persistence.jpa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.persistence.jpa.model.EMFService;
import de.vogella.gae.persistence.jpa.model.Todo;

@SuppressWarnings("serial")
public class PersistsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Todo todo = new Todo();

		todo.setSummary("This is my todo");

		EntityManager em = EMFService.get().createEntityManager();
		List<Todo> todos = null;
		try {
			em.persist(todo);
			Query q = em.createQuery("select t from Todo t");
			todos = new ArrayList(q.getResultList());
		} finally {
			em.close();
		}

		resp.setContentType("text/plain");
		if (todos != null) {
			resp.getWriter().println(
					"Hello, JPA. We have " + todos.size()
							+ " number of entries.");
		} else {
			resp.getWriter().println("Should not happen");
		}

	}
}
