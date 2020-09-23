package com.orders.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orders.management.entity.CustomerOrders;

/**
 * Customer Order Repository which interacts with the customer order table in
 * data base
 *
 * @author Amit Malik
 *
 */
@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrders, Long> {

	/**
	 * get number of orders placed by a customer
	 *
	 * @author Amit Malik
	 * @param customerId Customer Id
	 * @return number of orders placed by a customer
	 */
	@Query("SELECT COUNT(customerOrders) FROM CustomerOrders customerOrders WHERE customerOrders.customers.id = ?1")
	long countByCustomersId(long customerId);

	/**
	 * get Sum Of Discount Cost Of All Orders Of A Customer by Customer Id
	 *
	 * @author Amit Malik
	 * @param customerId Customer Id
	 * @return Sum Of Discount Cost Of All Orders Of A Customer
	 */
	@Query("SELECT SUM(customerOrders.discountCost) FROM CustomerOrders customerOrders WHERE customerOrders.customers.id = ?1")
	double getSumOfDiscountCostOfAllOrdersOfACustomer(long customerId);

	/**
	 * get all orders which are placed by a customer
	 *
	 * @author Amit Malik
	 * @param customerId Customer Id
	 * @return list of all orders which are placed by a customer
	 */
	List<CustomerOrders> findByCustomersId(long customerId);

}
