package com.cg.fds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Customer;

public interface ICustomerRepository {
	
	public Customer save(Customer customer);
	public Optional<Customer> findById(int id);

//	public Customer addCustomer(Customer customer);
//	public Customer updateCustomer(Customer customer);
//	public Customer removeCustomer(Customer customer);
//	public Customer viewCustomer(int id );
//	public List<Customer> viewAllCustomer(String restaurantname); 
}
