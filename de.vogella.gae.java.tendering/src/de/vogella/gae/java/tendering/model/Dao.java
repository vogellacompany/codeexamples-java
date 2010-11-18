package de.vogella.gae.java.tendering.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Dao {
	public static void save(Order order) {
		EntityManager em = EMFService.get().createEntityManager();
		Order savedOrder = em.find(Order.class, order.getNumber());
		if (savedOrder != null) {
			em.merge(order);
		} else {
			em.persist(order);
		}

	}

	public static Order get(String orderNumber) {
		EntityManager em = EMFService.get().createEntityManager();
		Order savedOrder = em.find(Order.class, orderNumber);
		return savedOrder;
	}

	public static List<Order> getOrders(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select * from order");
		List<Order> orders =  q.getResultList();
		return orders;
	}

}
