package com.cg.fds.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.customers.AddCustomer;
import com.cg.fds.dto.customers.CustomerDetails;
import com.cg.fds.dto.customers.RemoveCustomerRequest;
import com.cg.fds.dto.customers.UpdateCustomerRequest;
import com.cg.fds.entities.Address;
import com.cg.fds.entities.Customer;
import com.cg.fds.service.ICustomerService;
import com.cg.fds.util.AddressUtil;
import com.cg.fds.util.CustomerUtil;

@Validated
@RequestMapping("/customers")
@RestController
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private CustomerUtil customerUtil;

	@Autowired
	private AddressUtil addressUtil;

	@PostMapping("/add")
	public CustomerDetails addCustomer(@RequestBody @Valid AddCustomer request) {
		Customer customer = customerUtil.getCustomer();
		customer.setAge(request.getAge());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setGender(request.getGender());
		customer.setMobileNumber(request.getMobileNumber());
		customer.setEmail(request.getEmail());
		Address address = addressUtil.getAddress();
		address.setAddressId(addressUtil.generateId());
		address.setArea(request.getArea());
		address.setStreetNo(request.getStreetNo());
		address.setBuildingName(request.getBuildingName());
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setState(request.getState());
		address.setPincode(request.getPincode());
		customer.setAddress(address);
		return customerUtil.customerDetails(customerService.addCustomer(customer));
	}

	@PutMapping("/update")
	public CustomerDetails updateCustomer(@RequestBody @Valid UpdateCustomerRequest request) {

		Customer customer = customerService.viewCustomer(request.getId());
		customer.setAge(request.getAge());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setMobileNumber(request.getMobileNumber());
		customer.setEmail(request.getEmail());
		return customerUtil.customerDetails(customerService.updateCustomer(customer));
	}

	@DeleteMapping("/delete")
	public CustomerDetails deleteCustomer(@RequestBody @Valid RemoveCustomerRequest request) {
		Customer customer = customerService.viewCustomer(request.getId());
		return customerUtil.customerDetails(customerService.removeCustomer(customer));
	}

	@GetMapping("/view/{id}")
	public CustomerDetails viewCustomer(@PathVariable @NotBlank(message="Customer Id cannot be null") String id) {
		return customerUtil.customerDetails(customerService.viewCustomer(id));
	}
	
	@GetMapping("/viewall")
	public List<CustomerDetails> viewAllCustomer() {
		return customerUtil.customerDetailsList(customerService.viewAllCustomer());
	}

}
