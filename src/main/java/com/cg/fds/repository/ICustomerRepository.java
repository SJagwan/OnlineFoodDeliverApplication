package com.cg.fds.repository;

import java.util.List;

import com.cg.fds.entities.Customer;

public interface ICustomerRepository {

	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer viewCustomer(int id );
	public List<Customer> viewAllCustomer(String restaurantname); 
}
