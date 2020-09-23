package com.orders.management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customers Orders Entity which keeps the record of the orders placed by a
 * customer with pre discounted amount of the order and discounted amount of the
 * order and total amount a customer has to be pay
 *
 * @author Amit Malik
 *
 */
@Entity
@Table(name = "customer_orders")
@Data
@NoArgsConstructor
public class CustomerOrders implements Serializable {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -1054059344304035814L;

	/**
	 * Auto generated Id act as a primary key to the data base
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * reference of the Customer who order the product
	 */
	@ManyToOne
	@JoinColumn(name = "customers_id")
	private Customers customers;

	/**
	 * Product name the customer placed order
	 */
	@Column(name = "product_name")
	private String productName;

	/**
	 * Base Amount of the order placed by the customer, base amount like amount
	 * without discount
	 */
	@Column(name = "pre_discount_cost")
	private String preDiscountCount;

	/**
	 * discount or one order which the customer placed
	 */
	@Column(name = "discount_cost")
	private String discountCost;

	/**
	 * total cost of the order which the customer placed
	 */
	@Column(name = "total_cost")
	private String totalCost;
}
