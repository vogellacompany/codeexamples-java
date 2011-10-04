package de.vogella.gae.java.c2dm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.c2dm.dao.Dao;

@SuppressWarnings("serial")
public class ReceiveRegistration extends HttpServlet {
	private static final Logger log = Logger
			.getLogger(ReceiveRegistration.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.write("This servlet is used for receiving registration codes from devices.");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// Write back a response
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();

		// Register a new registration key
		String registrationId = (String) req.getParameter("registrationid");
		String deviceId = (String) req.getParameter("deviceid");
		resp.setContentType("text/plain");
		if (registrationId != null && registrationId.length() > 0
				&& deviceId != null && deviceId.length() > 0) {
			Dao.INSTANCE.add(deviceId, registrationId);
			log.info("Registered new device with registrationId="
					+ registrationId);
			writer.write("Successfully received registration code.");
		} else {
			log.info("Registration failed");
			writer.write("Registration failed.");
		}
	}
}
