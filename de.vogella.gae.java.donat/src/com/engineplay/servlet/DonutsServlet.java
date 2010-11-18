package com.engineplay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.engineplay.datastore.pojos.UserPrefs;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class DonutsServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UserPrefs userPrefs = UserPrefs.getPrefsForUser(user);
		try {

			String deleteOpt = req.getParameter("deleteBtn");
			if (deleteOpt == null) {
				int donuts = new Integer(req.getParameter("donuts")).intValue();
				int oldDonuts = new Integer(req.getParameter("olddonuts"))
						.intValue();

				userPrefs.setDonuts(donuts + oldDonuts);
				userPrefs.save();
			} else {
				String userId = req.getParameter("userId");
				userPrefs.setUserId(userId);
				userPrefs.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/Login");
	}
}
