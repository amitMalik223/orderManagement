package com.orders.management.commonutils;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.orders.management.entity.Customers;
import com.orders.management.repository.CustomerOrderRepository;
import com.orders.management.service.CustomerService;

/**
 * scheduler defined for
 *
 * @author Amit Malik
 *
 */
@Component
public class CustomerScheduler {

	/**
	 * Injected CustomerOrderRepository
	 */
	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	/**
	 * Injected CustomerService
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * Scheduler for customer to remind the customer about the order that he created
	 * and remind the promotion of the Customer from REGULAR to GOLD or PLATINUM
	 *
	 * This scheduler will run at 09:00 AM every Day
	 *
	 * @author Amit Malik
	 */
	@Scheduled(cron = "0 0 9 ? * *")
	public void cronJob() {
		System.out.println("09:00 AM scheduler works date : " + new Date());
		final List<Customers> customerList = this.customerService.getAllCustomers();
		customerList.forEach(customer -> {
			final long numOfOrderPlacedByACustomer = this.customerOrderRepository.countByCustomersId(customer.getId());
			if (numOfOrderPlacedByACustomer == 9 && numOfOrderPlacedByACustomer < 10) {
				CommonUtils.sendMail(ConstantUtils.CAN_BE_PROMOTED_TO_GOLD);
			} else if (numOfOrderPlacedByACustomer == 19 && numOfOrderPlacedByACustomer > 10
					&& numOfOrderPlacedByACustomer < 20) {
				CommonUtils.sendMail(ConstantUtils.CAN_BE_PROMOTED_TO_PLATINUM);
			}
		});
	}

}
