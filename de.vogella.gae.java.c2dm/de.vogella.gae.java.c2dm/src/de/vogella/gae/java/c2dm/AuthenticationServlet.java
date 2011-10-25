package de.vogella.gae.java.c2dm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import de.vogella.gae.java.c2dm.secure.SecureStorage;
import de.vogella.gae.java.c2dm.util.AuthenticationUtil;

@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String token = AuthenticationUtil.getToken(SecureStorage.USER,
				SecureStorage.PASSWORD);
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		if (token != null & token.length() > 0) {
			// Save data
			Entity entity = new Entity("token", "mytoken");
			entity.setProperty("authkey", token);
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			datastore.put(entity);
			writer.write("Response: " + token + "\n");
		}
		writer.write("Registration process finished.\n");

	}
}
