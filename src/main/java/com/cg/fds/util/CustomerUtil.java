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

	public String generateId() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomNum = random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}
	
	public CustomerDetails customerDetails(Customer customer) {
		
		CustomerDetails details= new CustomerDetails();
	    details.setFirstName(customer.getFirstName());
	    details.setLastName(customer.getLastName());
	    details.setGender(customer.getGender());
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
