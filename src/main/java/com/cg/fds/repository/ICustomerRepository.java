package com.cg.fds.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Customer;

public interface ICustomerRepository extends JpaRepository<Customer,String> {	
	Customer findCustomerBycustomerId(String customerId);
}
