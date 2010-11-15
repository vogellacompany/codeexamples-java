package de.vogella.gae.java.persistence.lowlevel;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class GetDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key todoKey = KeyFactory.createKey("Todo", "Todo1");
		Entity entity;
		try {
			entity = datastore.get(todoKey);
			String summary = (String) entity.getProperty("summary");

			resp.setContentType("text/plain");
			resp.getWriter()
					.println("Got the todo with the summary " + summary);
		} catch (EntityNotFoundException e) {
			resp.setContentType("text/plain");
			resp.getWriter().println("Entry not found.");
		}

	}
}
