package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Customer;

public interface ICustomerService {

	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer viewCustomer(int id );
	public List<Customer> viewAllCustomer(String restaurantname); 
}
