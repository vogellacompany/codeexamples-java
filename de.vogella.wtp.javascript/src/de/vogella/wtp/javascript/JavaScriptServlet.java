package de.vogella.wtp.javascript;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JavaScriptServlet
 */
public class JavaScriptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AtomicLong counter = new AtomicLong(0);   // We start with zero

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		// Set the session valid for 2 secs
		session.setMaxInactiveInterval(1);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		if (session.isNew()) {
			counter.incrementAndGet();
		}
		String s = "br.writeln(\"This site has been accessed "+ "<B>" + counter.get() +"</B><BR>"+"\");"; 
		out.println("var br=document;");
		out.println(s);
	}

	

}
