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
		System.out.println("Register a new registration key ");
		String registrationId = (String) req.getParameter("registrationid");
		String deviceId = (String) req.getParameter("deviceid");
		Dao.INSTANCE.add(deviceId, registrationId);
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.append("Thank you for registering");
	}

}
