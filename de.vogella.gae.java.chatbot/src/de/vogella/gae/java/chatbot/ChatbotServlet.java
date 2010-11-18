package de.vogella.gae.java.chatbot;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

@SuppressWarnings("serial")
public class ChatbotServlet extends HttpServlet {
	private static final XMPPService xmppService = XMPPServiceFactory
			.getXMPPService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] replies = {
				"Very good work! But please increase your efforts!",
				"Work harder! The deadline is approaching!",
				"I'm a little bit disappointed with the results of your work.",
				"You should work longer to be an example for the colleagues." };
		Random random = new Random();
		int nextInt = random.nextInt(4);
		Message message = xmppService.parseMessage(req);
		// To get the message send use message.getBody()
		Twitter twitter = new TwitterFactory().getInstance("vogellabot",
				"playing14");
		try {
			twitter.updateStatus(message.getBody());
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		Message reply = new MessageBuilder()
				.withRecipientJids(message.getFromJid())
				.withMessageType(MessageType.NORMAL).withBody(replies[nextInt])
				.build();
		xmppService.sendMessage(reply);
	}

}
