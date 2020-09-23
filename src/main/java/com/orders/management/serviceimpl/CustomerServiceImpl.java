package com.orders.management.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.management.commonutils.CommonUtils;
import com.orders.management.commonutils.ConstantUtils;
import com.orders.management.commonutils.CustomException;
import com.orders.management.entity.Customers;
import com.orders.management.repository.CustomerRepository;
import com.orders.management.service.CustomerService;

/**
 * Customer Service Implementation which implements Customer Service Interface
 * and this class contains all the business logic for Customer related API
 *
 * @author Amit Malik
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/**
	 * Injected CustomerRepository
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * save and update Customers
	 *
	 * @author Amit Malik
	 * @param customer Customers record From UI or Post Man to save and update
	 * @return saved or updated record of customer
	 * @throws CustomException if some custom exception is to be thrown from back
	 *                         end
	 */
	@Override
	public Customers saveCustomers(final Customers customer) throws CustomException {
		final long id = customer.getId();
		if (customer.getDiscount() == 0.0 && customer.getCategory() == null) {
			if (id != 0) {
				if (this.customerRepository.existsById(id)) {
					final Customers existingCustomers = this.customerRepository.findById(id);
					BeanUtils.copyProperties(customer, existingCustomers, CommonUtils.getNullPropertyNames(customer));
					existingCustomers.setId(id);
					return this.customerRepository.save(existingCustomers);
				} else {
					throw new CustomException(ConstantUtils.RECORD_NOT_EXISTS);
				}
			} else {
				customer.setCategory(ConstantUtils.REGULAR);
				customer.setDiscountCost("0.00");
				return this.customerRepository.save(customer);
			}
		} else {
			throw new CustomException(ConstantUtils.ONLY_NAME_MODIFIED);
		}
	}

	/**
	 * update some details of customer record from order like discount or category
	 *
	 * @author Amit Malik
	 * @param customer Customer Object to be updated from Orders
	 */
	@Override
	public void updateCustomerFromCustomerOrder(final Customers customer) {
		this.customerRepository.save(customer);
	}

	/**
	 * get single record of customer by id
	 *
	 * @author Amit Malik
	 * @param id Customer Id
	 * @return single record of Customers
	 */
	@Override
	public Customers getCustomerById(final long id) {
		return this.customerRepository.findById(id);
	}

	/**
	 * get customers list
	 *
	 * @author Amit Malik
	 * @return list of all customers
	 */
	@Override
	public List<Customers> getAllCustomers() {
		return this.customerRepository.findAll();
	}
}
