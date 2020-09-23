package com.orders.management.service;

import java.util.List;

import com.orders.management.commonutils.CustomException;
import com.orders.management.entity.Customers;

/**
 * Customer Service Interface which contains all the declared methods
 *
 * @author Amit Malik
 *
 */
public interface CustomerService {

	/**
	 * save and update Customers
	 *
	 * @author Amit Malik
	 * @param customer Customers record From UI or Post Man to save and update
	 * @return saved or updated record of customer
	 * @throws CustomException if some custom exception is to be thrown from back
	 *                         end
	 */
	Customers saveCustomers(Customers customer) throws CustomException;

	/**
	 * update some details of customer record from order like discount or category
	 *
	 * @author Amit Malik
	 * @param customer Customer Object to be updated from Orders
	 */
	void updateCustomerFromCustomerOrder(Customers customer);

	/**
	 * get single record of customer by id
	 *
	 * @author Amit Malik
	 * @param id Customer Id
	 * @return single record of Customers
	 */
	Customers getCustomerById(long id);

	/**
	 * get customers list
	 *
	 * @author Amit Malik
	 * @return list of all customers
	 */
	List<Customers> getAllCustomers();
}
