package de.vogella.gae.html;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class De_vogella_gae_htmlServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
