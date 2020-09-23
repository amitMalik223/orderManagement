package com.orders.management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customers Entity
 *
 * @author Amit Malik
 *
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customers implements Serializable {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -4648857971917843704L;

	/**
	 * Auto generated Id act as a primary key to the data base
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * First name of the customer
	 */
	@Column(name = "first_name", length = 50)
	private String firstName;

	/**
	 * Last name of the customer
	 */
	@Column(name = "last_name", length = 50)
	private String lastName;

	/**
	 * Category of the customer regular, gold, platinum
	 */
	@Column(name = "category")
	private String category;

	/**
	 * discount in percentage provided
	 */
	@Column(name = "discount")
	private double discount;

	/**
	 * total discounted amount which is given back to the customer
	 */
	@Column(name = "discount_cost")
	private String discountCost;
}
