package de.vogella.gae.java.c2dm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthenticationUtil {

	public static String getToken(String email, String password)
			throws IOException {
		// Create the post data
		// Requires a field with the email and the password
		StringBuilder builder = new StringBuilder();
		builder.append("Email=").append(email);
		builder.append("&Passwd=").append(password);
		builder.append("&accountType=GOOGLE");
		builder.append("&source=Google-C2DM-Example");
		builder.append("&service=ac2dm");

		// Setup the Http Post
		byte[] data = builder.toString().getBytes();
		URL url = new URL("https://www.google.com/accounts/ClientLogin");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setUseCaches(false);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", Integer.toString(data.length));
		OutputStream output = con.getOutputStream();
		output.write(data);
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String[] split = reader.readLine().split("=");
		// Finally get the authentication token
		String clientAuthToken = split[1];
		// To something useful with it
		return clientAuthToken;
	}
}
