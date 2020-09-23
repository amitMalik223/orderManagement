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
import com.orders.management.entity.Customers;
import com.orders.management.service.CustomerService;

/**
 * Used as rest controller to which contains all the API
 *
 * @author Dell
 *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	/**
	 * injected Customer Service
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * save and update Customers
	 *
	 * @author Amit Malik
	 * @param customer Customers record From UI or Post Man to save and update
	 * @param response response will be used to send the returned object as a
	 *                 response in Map
	 * @return map which contains saved or updated record of customer
	 * @throws CustomException if some custom exception is to be thrown from back
	 *                         end
	 */
	@PostMapping("/saveCustomers")
	public Map<String, Object> saveCustomers(@RequestBody final Customers customer, final HttpServletResponse response)
			throws CustomException {
		final Map<String, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(ConstantUtils.CUSTOMERS, this.customerService.saveCustomers(customer));
		return RestServiceTemplateUtils.createdSuccessResponse(mapToReturn, response);
	}

	/**
	 * get list of all customers
	 *
	 * @author Amit Malik
	 * @param response response will be used to send the returned object as a
	 *                 response in Map
	 * @return map which contains list all customers
	 */
	@GetMapping("/getAllCustomers")
	public Map<String, Object> getAllCustomers(final HttpServletResponse response) {
		final Map<String, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(ConstantUtils.CUSTOMERS_LIST, this.customerService.getAllCustomers());
		return RestServiceTemplateUtils.getRecordSuccessResponse(mapToReturn, response);
	}

	/**
	 * get single record of customer by Customer Id
	 *
	 * @author Amit Malik
	 * @param id       Customer Id
	 * @param response response will be used to send the returned object as a
	 *                 response in Map
	 * @return map which contains single record of customer
	 */
	@GetMapping("/getCustomerById")
	public Map<String, Object> getCustomerRecordById(@RequestParam("id") final long id,
			final HttpServletResponse response) {
		final Map<String, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(ConstantUtils.CUSTOMERS, this.customerService.getCustomerById(id));
		return RestServiceTemplateUtils.getRecordSuccessResponse(mapToReturn, response);
	}
}
