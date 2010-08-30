package de.vogella.gae.java.todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StupidServletCount extends HttpServlet {
	static  int counter = 0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		counter++;
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/plain");
		writer.print(counter);
	}
}
