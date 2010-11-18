package de.vogella.gae.feedback;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FeedbackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String email = req.getParameter("email");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = description;
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("Lars.Vogel@googlemail.com",
					"Lars Vogel"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email, name));
			msg.setSubject("Feedback");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (Exception e) {
			resp.setContentType("text/plain");
			resp.getWriter().println("Something went wrong. Please try again.");
			throw new RuntimeException(e);
		}

		resp.setContentType("text/plain");
		resp.getWriter().println(
				"Thank you for your feedback. An Email has been send out.");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String email = req.getParameter("email");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = description;
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("Lars.Vogel@googlemail.com",
					"Lars Vogel"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email, name));
			msg.setSubject("Feedback");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (Exception e) {
			resp.setContentType("text/plain");
			resp.getWriter().println("Something went wrong. Please try again.");
			throw new RuntimeException(e);
		}

		resp.setContentType("text/plain");
		resp.getWriter().println(
				"Thank you for your feedback. An Email has been send out.");
	}
}
