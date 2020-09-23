package com.orders.management.commonutils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Class which contains some common methods
 *
 * @author Amit Malik
 *
 */
@Slf4j
@Component
public final class CommonUtils {

	/**
	 * @author Amit Malik
	 */
	private CommonUtils() {
		// private constructor to prevent instantiation
	}

	/**
	 * ignore null properties
	 *
	 * @param source source
	 * @return array of null properties
	 */
	public static String[] getNullPropertyNames(final Object source) {
		final Set<String> emptyNames = getSetOfNullPropertyNames(source);
		final String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * @param source source
	 * @return set of null property names
	 */

	private static Set<String> getSetOfNullPropertyNames(final Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		final java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		final Set<String> emptyNames = new HashSet<>();
		for (final java.beans.PropertyDescriptor pd : pds) {
			try {
				final Object srcValue = src.getPropertyValue(pd.getName());
				if (srcValue == null) {
					emptyNames.add(pd.getName());
				}
			} catch (final RuntimeException e) {
				if (log.isInfoEnabled()) {
					log.info(e.getMessage());
				}
			} catch (final Exception e) {
				if (log.isInfoEnabled()) {
					log.info(e.getMessage());
				}
			}

		}
		return emptyNames;
	}

	/**
	 * Send the email
	 *
	 * @author Amit Malik
	 * @param promotedTo Customers Promoted To category
	 */
	public static void sendMail(final String promotedTo) {
		if (ConstantUtils.CAN_BE_PROMOTED_TO_GOLD.equalsIgnoreCase(promotedTo)) {
			System.out.println(
					"You have placed	9 orders with us. Buy one more stuff and you will be promoted to Gold customer and enjoy 10% discounts!");
		} else if (ConstantUtils.CAN_BE_PROMOTED_TO_PLATINUM.equalsIgnoreCase(promotedTo)) {
			System.out.println(
					"You have placed	19 orders with us. Buy one more stuff and you will be promoted to Platinum customer and enjoy 20% discounts!");
		}
	}
}
