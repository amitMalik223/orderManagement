package com.orders.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orders.management.entity.Customers;

/**
 * Customer Repository which is used to interact with the data base
 *
 * @author Amit Malik
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

	/**
	 * get Single record of Customers
	 *
	 * @author Amit Malik
	 * @param id Customers Id
	 * @return single record of customers
	 */
	Customers findById(long id);
}
