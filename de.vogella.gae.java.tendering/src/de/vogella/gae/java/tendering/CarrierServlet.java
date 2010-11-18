package de.vogella.gae.java.tendering;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.tendering.model.Dao;
import de.vogella.gae.java.tendering.model.Order;

public class CarrierServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String number = req.getParameter("order");
		Order order = Dao.get(number);
		resp.setContentType("text/plain");
		if (order==null) {
			resp.setStatus(404);
			resp.getWriter().println("Order not found with: " + number);
		}
		if (order.getCarrier()==null) {
			resp.getWriter().println("Carrier currently not assigned to " + order.getNumber());
		}
		else {
			resp.getWriter().println("Carrier assigned to order: " + order.getCarrier());
		}
	}
}
