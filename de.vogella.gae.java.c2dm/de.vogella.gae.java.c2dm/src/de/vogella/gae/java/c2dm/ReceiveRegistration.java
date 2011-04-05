package de.vogella.gae.java.c2dm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.c2dm.dao.Dao;

@SuppressWarnings("serial")
public class ReceiveRegistration extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.write("This servlet is alive and kicking");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// Register a new registration key
		String registrationId = (String) req.getParameter("registrationid");
		String deviceId = (String) req.getParameter("deviceid");
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		if (registrationId != null && registrationId.length() > 0
				&& deviceId != null && deviceId.length() > 0) {
			Dao.INSTANCE.add(deviceId, registrationId);
			writer.append("Thank you for registering");
		} else {
			writer.append("Registration failed");
		}
	}
}
