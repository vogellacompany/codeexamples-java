package de.vogella.gae.java.todo;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.todo.dao.Dao;
import de.vogella.gae.java.todo.model.MyUser;

public class EmailServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(EmailServlet.class
			.getName());

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props = new Properties();
		Session email = Session.getDefaultInstance(props, null);
		try {
			MimeMessage message = new MimeMessage(email, req.getInputStream());
			String summary = message.getSubject();
			String description = getText(message);
			Address[] addresses = message.getFrom();
			MyUser user = Dao.INSTANCE.getUser(addresses[0].toString());
			log.severe(addresses[0].toString());
			if (user != null) {
				Dao.INSTANCE.add(user.getUserId(), summary, description, "");
			} else {
				log.severe("User was null");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the primary text content of the message.
	 */
	private String getText(Part p) throws MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}

}
