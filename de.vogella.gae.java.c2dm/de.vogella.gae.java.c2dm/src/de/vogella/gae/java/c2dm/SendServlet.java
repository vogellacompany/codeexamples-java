package de.vogella.gae.java.c2dm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.c2dm.dao.Dao;
import de.vogella.gae.java.c2dm.model.RegisteredDevice;


public class SendServlet extends HttpServlet{
	
	private final static String AUTH = "authentication";
	private final static String auth_key ="DQAAAMUAAABLNakYQJ4RGzTc8mrp-7wX4NLyfwZx27o_Gyp6R3zOmVvGxNpeizMGdnorRtBUtyH3xkmHJ8NzY1hXBV9RT4q1miIjC86qZkXw6WW7qJli96bAVfSYegy4KpK7WyNHow1AM3pekYqvQLDR9CaRjuTVvH7bxryWce-sUFp1ME-p_Px1LJ_Ua8dxwbkefEhK5q6oD4rF7NzQ-bOZnW7MD-aOktqVhWcUlX7bj0uc3oVKJnQDgNcfAFmPt4NQc1c8F3o-IleEPpisUCoezuV6SJ0R";
	
	private static final String UPDATE_CLIENT_AUTH = "Update-Client-Auth";
	
	public static final String PARAM_REGISTRATION_ID = "registration_id";

	public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";
	
	private static final Logger log = Logger.getLogger(SendServlet.class.getName());

	  
	public static final String PARAM_COLLAPSE_KEY = "collapse_key";
	
	private static final String UTF8 = "UTF-8";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<RegisteredDevice> devices = Dao.INSTANCE.getDevices();
		StringBuilder sb = new StringBuilder();
		for (RegisteredDevice device : devices) {
			sb.append(sendMessage(device.getRegistrationId()));
			
		}
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.write(sb.toString());
		
	}
	
	private String  sendMessage(String registrationId) throws IOException {
		// Send a sync message to this Android device.
		StringBuilder postDataBuilder = new StringBuilder();
		postDataBuilder.append(PARAM_REGISTRATION_ID).append("=")
				.append(registrationId);

		postDataBuilder.append("&").append(PARAM_COLLAPSE_KEY).append("=")
				.append("0");

		postDataBuilder.append("&").append("data.payload").append("=")
				.append(URLEncoder.encode("A new message has arrived", UTF8));

		byte[] postData = postDataBuilder.toString().getBytes(UTF8);

		// Hit the C2DMdm URL.

		URL url = new URL("https://android.clients.google.com/c2dm/send");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length",
				Integer.toString(postData.length));
		conn.setRequestProperty("Authorization", "GoogleLogin auth="
				+ auth_key );

		OutputStream out = conn.getOutputStream();
		out.write(postData);
		out.close();

		int responseCode = conn.getResponseCode();
		

		log.info(String.valueOf(responseCode));
		// Validate the response code

		if (responseCode == 401 || responseCode == 403) {
			// The token is too old - return false to retry later, will
			// fetch the token
			// from DB. This happens if the password is changed or token
			// expires. Either admin
			// is updating the token, or Update-Client-Auth was received by
			// another server,
			// and next retry will get the good one from database.
			log.severe("Unauthorized - need token");
		}

		// Check for updated token header
		String updatedAuthToken = conn.getHeaderField(UPDATE_CLIENT_AUTH);
		if (updatedAuthToken != null && !auth_key.equals(updatedAuthToken)) {
			log.info("Got updated auth token from datamessaging servers: "
							+ updatedAuthToken);
		}

		String responseLine = new BufferedReader(new InputStreamReader(
				conn.getInputStream())).readLine();

		// NOTE: You *MUST* use exponential backoff if you receive a 503
		// response code.
		// Since App Engine's task queue mechanism automatically does this
		// for tasks that
		// return non-success error codes, this is not explicitly
		// implemented here.
		// If we weren't using App Engine, we'd need to manually implement
		// this.
		if (responseLine == null || responseLine.equals("")) {
			log.info("Got " + responseCode
					+ " response from Google AC2DM endpoint.");
			throw new IOException(
					"Got empty response from Google AC2DM endpoint.");
		}

		String[] responseParts = responseLine.split("=", 2);
		if (responseParts.length != 2) {
			log.warning("Invalid message from google: " + responseCode
					+ " " + responseLine);
			throw new IOException("Invalid response from Google "
					+ responseCode + " " + responseLine);
		}

		if (responseParts[0].equals("id")) {
			log.info("Successfully sent data message to device: "
					+ responseLine);
		}

		if (responseParts[0].equals("Error")) {
			String err = responseParts[1];
			log.warning(
					"Got error response from Google datamessaging endpoint: "
							+ err);
			// No retry.
			throw new IOException(err);
		}
		return responseLine + " " +String.valueOf(responseCode);
	}
}
