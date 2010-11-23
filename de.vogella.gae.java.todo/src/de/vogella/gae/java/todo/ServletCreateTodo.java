package de.vogella.gae.java.todo;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.vogella.gae.java.todo.dao.Dao;
import de.vogella.gae.java.todo.model.MyUser;

@SuppressWarnings("serial")
public class ServletCreateTodo extends HttpServlet {
	private static final Logger log = Logger.getLogger(ServletCreateTodo.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		log.severe("Creating new todo Message ");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}

		// Check if users email is already known
		MyUser savedUser = Dao.INSTANCE.getUser(user.getEmail());
		if (savedUser == null) {
			log.severe("Save new user");
			Dao.INSTANCE.persistsUser(new MyUser(user.getUserId(), user
					.getEmail()));
		} else {
			log.severe("I have this user already");
		}

		String summary = checkNull(req.getParameter("summary"));
		String longDescription = checkNull(req.getParameter("description"));
		String url = checkNull(req.getParameter("url"));

		Dao.INSTANCE.add(user.getUserId(), summary, longDescription, url);

		resp.sendRedirect("/TodoApplication.jsp");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
