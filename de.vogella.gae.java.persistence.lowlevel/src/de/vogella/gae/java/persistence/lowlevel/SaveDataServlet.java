package de.vogella.gae.java.persistence.lowlevel;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class SaveDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Entity entity = new Entity("Todo", "Todo1");
		// Alternatively use
		// Key todoKey = KeyFactory.createKey("Todo", "Todo1");
		// Entity entity = new Entity(todoKey);
		entity.setProperty("summary", "This is my summary");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);

		resp.setContentType("text/plain");
		resp.getWriter().println("Saved data");
	}
}
