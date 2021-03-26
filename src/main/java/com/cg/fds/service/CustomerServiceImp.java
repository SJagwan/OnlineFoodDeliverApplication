package com.cg.fds.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.exception.CustomerNotFoundException;
import com.cg.fds.exception.InvalidCustomerAddressException;
import com.cg.fds.exception.InvalidCustomerException;
import com.cg.fds.exception.InvalidCustomerPhoneNumberException;
import com.cg.fds.exception.RemoveCustomerException;
import com.cg.fds.exception.UpdateCustomerException;
import com.cg.fds.repository.ICustomerRepository;

public class CustomerServiceImp implements ICustomerService {
	@Autowired
	ICustomerRepository customerRepository;
	
	

	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}
	
	@Override
	public Customer addCustomer(Customer customer) {
		validateCustomer(customer);
//		validatePhone(customer.getMobileNumber());
//		Address address=customer.getAddress();
//		Address savedAddress=addressRepository.save(address);
//		customer.setAddress(savedAddress);
//		customer.setCustomerId(customer.id);
		Customer saved = customerRepository.save(customer);
//		FoodCart cart=new FoodCart();
//		cart.setCustomer(saved);
		//cartRepository.save(cart);
		return saved;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		validateCustomer(customer);
		String customerId = customer.getCustomerId();
		boolean exist=customerRepository.existsById("1");
		if(!exist)
		{
			throw new UpdateCustomerException("Customer doesn't exist for id =" + customer.getCustomerId());
		}
		Customer updateCustomer = customerRepository.save(customer);

		return updateCustomer;
	}

	@Override
	public Customer removeCustomer(Customer customer) {
		validateCustomer(customer);
		String customerId = customer.getCustomerId();
		boolean exist=customerRepository.existsById("1");
		if(!exist)
		{
			throw new RemoveCustomerException("Customer doesn't exist for id =" + customer.getCustomerId());
		}
		 customerRepository.delete(customer);
		return customer;
	}

	@Override
	public Customer viewCustomer(String id) {
		Optional<Customer> viewCustomer = customerRepository.findById(id);
		if (!viewCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer doesn't exist for id =" + id);
		}
		return viewCustomer.get();
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

	public void validatePhone(String phoneNumber) {
		if (phoneNumber == null) {
			throw new InvalidCustomerPhoneNumberException("Customer Phone Number cannot be null");
		}
}
	public void validateAddress(String address) {
		if (address == null) {
			throw new InvalidCustomerAddressException("Customer Address cannot be null");
		}
}
}
