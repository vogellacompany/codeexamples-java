package de.vogella.eclipsemag.wave;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class De_vogella_eclipsemag_waveServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
