package de.vogella.java.c2dm.server;

import java.io.IOException;

import de.vogella.java.c2dm.server.util.MessageUtil;

public class SendMessageToDevice {

	public static void main(String[] args) throws IOException {
		// "Message to your device." is the message we will send to the Android
		// app
		int responseCode = MessageUtil.sendMessage(
				ServerConfiguration.AUTHENTICATION_TOKEN,
				ServerConfiguration.REGISTRATION_ID,
				"Message from AndroidOpen2.");
		System.out.println(responseCode);
	}
}
