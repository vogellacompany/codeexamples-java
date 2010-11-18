package de.vogella.gae.java.tendering;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.tendering.model.Dao;
import de.vogella.gae.java.tendering.model.Order;

@SuppressWarnings("serial")
public class SaveServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String number = req.getParameter("order");
		String startLoc = req.getParameter("start");
		String endLoc = req.getParameter("end");
		String priceString = req.getParameter("price");
		String quantityString = req.getParameter("quantity");
		int quantity = Integer.parseInt(quantityString);
		float price = Float.parseFloat(priceString);
		
		Order order = new Order(number, startLoc, endLoc, quantity, price);
		Dao.save(order);
		resp.setContentType("text/plain");
		resp.getWriter().println("Successfully saved order number " + number);
	}
}
