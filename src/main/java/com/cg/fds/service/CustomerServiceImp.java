package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Customer;
import com.cg.fds.exception.InvalidCustomerException;
import com.cg.fds.repository.ICustomerRepository;

public class CustomerServiceImp implements ICustomerService {
	@Autowired
	ICustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		validateCustomer(customer);
		Customer addCustomer = customerRepository.save(customer);

		return addCustomer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		validateCustomer(customer);
		Customer updateCustomer = customerRepository.save(customer);

		return updateCustomer;
	}

	@Override
	public Customer removeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomer(int id) {
		Optional<Customer> viewCustomer = customerRepository.findById(id);
		Customer customer = null;
		if (viewCustomer.isPresent()) {
			customer = viewCustomer.get();
		}
		return customer;
	}

	@Override
	public List<Customer> viewAllCustomer(String restaurantname) {
		// TODO Auto-generated method stub
		return null;
	}

	public void validateCustomer(Customer customer) {
		if (customer == null) {
			throw new InvalidCustomerException("Customer cannot be null");
		}

	}

}
