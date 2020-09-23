package com.orders.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orders.management.commonutils.ConstantUtils;
import com.orders.management.commonutils.CustomException;
import com.orders.management.commonutils.RestServiceTemplateUtils;
import com.orders.management.entity.CustomerOrders;
import com.orders.management.service.CustomerOrderService;

/**
 * Customer Order Controller for Order REST API
 *
 * @author Amit Malik
 *
 */
@RestController
@RequestMapping("/api/customerOrders")
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;

	/**
	 * customer will place an order
	 *
	 * @author Amit Malik
	 * @param customerOrders Customer Order Object which is be saved
	 * @param response       response will be used to send the returned object as a
	 *                       response in Map
	 * @return map which contains saved record of the order placed by the customer
	 * @throws CustomException if we have to through an exception
	 */
	@PostMapping("/placeAnOrder")
	public Map<String, Object> placeAnOrder(@RequestBody final CustomerOrders customerOrders,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(ConstantUtils.CUSTOMER_ORDERS, this.customerOrderService.placeAnOrder(customerOrders));
		return RestServiceTemplateUtils.createdSuccessResponse(mapToReturn, response);
	}

	/**
	 * get all orders which are placed by a customer
	 *
	 * @author Amit Malik
	 * @param customerId Customer Id
	 * @param response   response will be used to send the returned object as a
	 *                   response in Map
	 * @return map contains list of all orders which are placed by a customer
	 */
	@GetMapping("/getOrdersPlacedByCustomerByCustomerId")
	public Map<String, Object> getOrdersPlacedByCustomerByCustomerId(@RequestParam("customerId") final long customerId,
			final HttpServletResponse response) {
		final Map<String, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(ConstantUtils.CUSTOMER_ORDERS_LIST,
				this.customerOrderService.getOrdersPlacedByCustomerByCustomerId(customerId));
		return RestServiceTemplateUtils.getRecordSuccessResponse(mapToReturn, response);
	}

	/**
	 * get list of all orders
	 *
	 * @author Amit Malik
	 * @param response response will be used to send the returned object as a
	 *                 response in Map
	 * @return map which contains list all orders
	 */
	@GetMapping("/getAllOrders")
	public Map<String, Object> getAllOrders(final HttpServletResponse response) {
		final Map<String, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(ConstantUtils.ALL_CUSTOMERS_ORDERS_LIST, this.customerOrderService.getAllOrders());
		return RestServiceTemplateUtils.getRecordSuccessResponse(mapToReturn, response);
	}
}
