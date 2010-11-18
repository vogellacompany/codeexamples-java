package de.vogella.gae.java.tendering;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.tendering.model.Dao;
import de.vogella.gae.java.tendering.model.Order;

public class DeclineServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		Order order = Dao.get(id);
		order.setCarrier("Declined by Carrier Joe the helpful trucker");
		order.setResponded(true);
		Dao.save(order);
		resp.sendRedirect("/List.jsp");
	}
}
