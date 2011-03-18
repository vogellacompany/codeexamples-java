package de.vogella.gae.java.c2dm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.write("This servlet is alive and kicking");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Entity entity = new Entity("key", "");
        // Alternatively use
        // Key todoKey = KeyFactory.createKey("Todo", "Todo1");
        // Entity entity = new Entity(todoKey);
        entity.setProperty("summary", "This is my summary");
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
        datastore.put(entity);
	}

}
