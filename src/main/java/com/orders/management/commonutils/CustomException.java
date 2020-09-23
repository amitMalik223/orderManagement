package com.orders.management.commonutils;

import org.springframework.stereotype.Component;

/**
 * It is use for throw custom exceptions like record already exists
 *
 * @author Amit Malik
 *
 */
@Component
public class CustomException extends Exception {

	/**
	 * Serial Version ID for Serialization
	 */
	private static final long serialVersionUID = -645241288085649231L;

	/**
	 * default non parameterized constructor
	 */
	public CustomException() {

	}

	/**
	 *
	 * @param message Message which we have to show when Customer Exception throws
	 */
	public CustomException(final String message) {
		super(message);
	}

}
