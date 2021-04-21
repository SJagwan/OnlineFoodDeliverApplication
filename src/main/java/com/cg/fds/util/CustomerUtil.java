package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.fds.dto.customers.CustomerDetails;
import com.cg.fds.entities.Customer;

@Component
public class CustomerUtil {
	public Customer getCustomer() {
		return new Customer();
	}

	
	public CustomerDetails customerDetails(Customer customer) {
		
		CustomerDetails details= new CustomerDetails();
	    details.setFirstName(customer.getFirstName());
	    details.setLastName(customer.getLastName());
	    details.setGender(customer.getGender());
	    details.setCustomerId(customer.getCustomerId());
	    return details;
	}
	public List<CustomerDetails> customerDetailsList(List<Customer> list){
		List<CustomerDetails> cd=new ArrayList<>();
		for(Customer customer:list) {
			cd.add(customerDetails(customer));
		}
		return cd;
	}
}
