package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Customer;

@Component
public class CustomerUtil {
	public Customer getCustomer() {
		return new Customer();
	}

}
