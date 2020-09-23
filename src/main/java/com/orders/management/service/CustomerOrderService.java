package com.orders.management.service;

import java.util.List;

import com.orders.management.commonutils.CustomException;
import com.orders.management.entity.CustomerOrders;

/**
 * Customer Order Service Interface which was implemented by Order Service
 * Implementation Class for Business logic
 *
 * @author Amit Malik
 *
 */
public interface CustomerOrderService {

	/**
	 * customer will place an order
	 *
	 * @author Amit Malik
	 * @param customerOrders Customer Order Object which is be saved
	 * @return saved record of the order placed by the customer
	 * @throws CustomException if we have to through an exception
	 */
	CustomerOrders placeAnOrder(CustomerOrders customerOrders) throws CustomException;

	/**
	 * get all orders which are placed by a customer
	 *
	 * @author Amit Malik
	 * @param customerId Customer Id
	 * @return list of all orders which are placed by a customer
	 */
	List<CustomerOrders> getOrdersPlacedByCustomerByCustomerId(long customerId);

	/**
	 * get list of all orders
	 *
	 * @author Amit Malik
	 * @return list all orders
	 */
	List<CustomerOrders> getAllOrders();
}
