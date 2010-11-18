package com.engineplay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.engineplay.datastore.pojos.UserPrefs;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String navBar, form = null;
		int donuts = 0;
		if (user == null) {
			navBar = "<p>Welcome! <a href=\"" + userService.createLoginURL("/")
					+ "\">Sign in or register</a> to customize.</p>";
			form = "";
		} else {
			UserPrefs userPrefs = UserPrefs.getPrefsForUser(user);

			if (userPrefs != null) {
				donuts = userPrefs.getDonuts();
			}
			navBar = "<p>Welcome, " + user.getEmail() + "! You can <a href=\""
					+ userService.createLogoutURL("/") + "\">sign out</a>.</p>";
			form = "<form action=\"/donuts\" method=\"post\">"
					+ "<label for=\"donuts\">"
					+ "Need more donuts?:"
					+ "</label>"
					+ "<input name=\"donuts\" id=\"donuts\" type=\"text\" size=\"4\" />"
					+ " <input name=\"olddonuts\"&nbsp; type=\"hidden\" value=\""
					+ donuts
					+ "\" /> "
					+ " <input name=\"userId\"&nbsp; type=\"hidden\" value=\""
					+ user.getEmail()
					+ "\" /> "
					+ "&nbsp;&nbsp; <input type=\"submit\" value=\"&nbsp; ADD&nbsp; \" /><br><input type=\"submit\"&nbsp; name =\"deleteBtn\" value=\"&nbsp; DELETE ME!&nbsp; \" />"
					+ "</form>";

		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Engine Play - Donuts</title</head><body>");
		out.println(navBar);
		if (donuts != 0)
			out.println("<p>Donuts you have: " + donuts + "</p>");
		else
			out.println("<p> No donuts you have <img src=\"http://www.pakzilla.com/wp-includes/images/smilies/icon_sad.gif\" alt=\":(\" class=\"wp-smiley\">  </p>");
		out.println(form);
		out.print("</body></html>");
	}
}
