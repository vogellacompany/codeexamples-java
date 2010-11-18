package de.vogella.gae.java.apiproxy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.apphosting.api.ApiProxy;

@SuppressWarnings("serial")
public class DelegateTestServlet extends HttpServlet {
	static {
		ApiProxy.setDelegate(new ProfilingDelegate(ApiProxy.getDelegate()));
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
