package com.orders.management.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.management.commonutils.CommonUtils;
import com.orders.management.commonutils.ConstantUtils;
import com.orders.management.commonutils.CustomException;
import com.orders.management.entity.CustomerOrders;
import com.orders.management.entity.Customers;
import com.orders.management.repository.CustomerOrderRepository;
import com.orders.management.service.CustomerOrderService;
import com.orders.management.service.CustomerService;

/**
 * Customer Order Service Impl class which define all the methods of customer
 * order service interface
 *
 * @author Amit Malik
 *
 */
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	/**
	 * injected CustomerService for getting Customer Information
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * injected CustomerOrderRepository
	 */
	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	/**
	 * customer will place an order
	 *
	 * @author Amit Malik
	 * @param customerOrders Customer Order Object which is be saved
	 * @return saved record of the order placed by the customer
	 * @throws CustomException if we have to through an exception
	 */
	@Override
	public CustomerOrders placeAnOrder(final CustomerOrders customerOrders) throws CustomException {
		if (customerOrders.getPreDiscountCount() != null && customerOrders.getDiscountCost() == null
				&& customerOrders.getTotalCost() == null) {
			if (customerOrders.getId() == 0) {
				try {
					Double.parseDouble(customerOrders.getPreDiscountCount());
					customerOrders
							.setPreDiscountCount(new BigDecimal(String.valueOf(customerOrders.getPreDiscountCount()))
									.setScale(2, RoundingMode.CEILING).toPlainString());
					return this.placeTheOrderWithOtherThingsUpdated(customerOrders);
				} catch (final NumberFormatException numberFormatException) {
					numberFormatException.printStackTrace();
					throw new CustomException(ConstantUtils.ONLY_NUMERIC_DECIMAL_AMOUNT_ENTERED);
				}
			} else {
				throw new CustomException(ConstantUtils.ORDER_ONLY_CREATED_NOT_UPDATED);
			}
		} else {
			throw new CustomException(ConstantUtils.ONLY_UNIT_COST_ALLOWED);
		}
	}

	/**
	 * place the order with other things modified or updated
	 *
	 * @author Amit Malik
	 * @param customerOrders Customer Order Object from which Customer is updated
	 *                       and discount cost is calculated
	 * @throws CustomException if we have to through an exception
	 */
	private CustomerOrders placeTheOrderWithOtherThingsUpdated(final CustomerOrders customerOrders)
			throws CustomException {
		if (customerOrders.getCustomers() != null) {
			final Customers customerObj = this.customerService.getCustomerById(customerOrders.getCustomers().getId());
			if (customerObj != null) {
				return this.calculateDiscountAndTotalCost(customerOrders, customerObj);
			} else {
				throw new CustomException(ConstantUtils.CUSTOMER_NOT_EXISTS);
			}
		} else {
			throw new CustomException(ConstantUtils.CUSTOMER_MANDATORY);
		}
	}

	/**
	 * calculate the discount and update all the costs in customer and customer
	 * order record
	 *
	 * @author Amit Malik
	 * @param customerOrders Customer Order Object i which some fields are to be
	 *                       setted
	 * @param customerObj    Customer Object which is to be updated
	 */
	private CustomerOrders calculateDiscountAndTotalCost(final CustomerOrders customerOrders,
			final Customers customerObj) {
		final double discountCostOnOrder = Double.parseDouble(customerOrders.getPreDiscountCount())
				* customerObj.getDiscount() / 100;
		final double totalCost = Double.parseDouble(customerOrders.getPreDiscountCount()) - discountCostOnOrder;
		customerOrders.setDiscountCost(
				new BigDecimal(String.valueOf(discountCostOnOrder)).setScale(2, RoundingMode.CEILING).toPlainString());
		customerOrders.setTotalCost(
				new BigDecimal(String.valueOf(totalCost)).setScale(2, RoundingMode.CEILING).toPlainString());
		final CustomerOrders savedCustomerOrdersObject = this.customerOrderRepository.save(customerOrders);
		final double sumOfDiscountCostOfAllOrdersOfACustomer = this.customerOrderRepository
				.getSumOfDiscountCostOfAllOrdersOfACustomer(customerObj.getId());
		customerObj.setDiscountCost(new BigDecimal(String.valueOf(sumOfDiscountCostOfAllOrdersOfACustomer))
				.setScale(2, RoundingMode.CEILING).toPlainString());
		this.changeTheCateogryAndDiscountOfCustomer(customerObj);
		return savedCustomerOrdersObject;
	}

	/**
	 * change The Cateogry And Discount Of Customer based on order placed by a
	 * customer
	 *
	 * @author Amit Malik
	 * @param customerObj Customer Object to be updated
	 */
	public void changeTheCateogryAndDiscountOfCustomer(final Customers customerObj) {
		final long numOfOrderPlacedByACustomer = this.customerOrderRepository.countByCustomersId(customerObj.getId());
		if (numOfOrderPlacedByACustomer >= 10 && numOfOrderPlacedByACustomer < 20) {
			customerObj.setCategory(ConstantUtils.GOLD);
			customerObj.setDiscount(10.00);
		} else if (numOfOrderPlacedByACustomer >= 20) {
			customerObj.setCategory(ConstantUtils.PLATINUM);
			customerObj.setDiscount(20.00);
		}
		if (numOfOrderPlacedByACustomer == 9 && numOfOrderPlacedByACustomer < 10) {
			CommonUtils.sendMail(ConstantUtils.CAN_BE_PROMOTED_TO_GOLD);
		} else if (numOfOrderPlacedByACustomer == 19 && numOfOrderPlacedByACustomer > 10
				&& numOfOrderPlacedByACustomer < 20) {
			CommonUtils.sendMail(ConstantUtils.CAN_BE_PROMOTED_TO_PLATINUM);
		}
		this.customerService.updateCustomerFromCustomerOrder(customerObj);
	}

	/**
	 * get all orders which are placed by a customer
	 *
	 * @author Amit Malik
	 * @param customerId Customer Id
	 * @return list of all orders which are placed by a customer
	 */
	@Override
	public List<CustomerOrders> getOrdersPlacedByCustomerByCustomerId(final long customerId) {
		return this.customerOrderRepository.findByCustomersId(customerId);
	}

	/**
	 * get list of all orders
	 *
	 * @author Amit Malik
	 * @return list all orders
	 */
	@Override
	public List<CustomerOrders> getAllOrders() {
		return this.customerOrderRepository.findAll();
	}
}
