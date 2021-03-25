package com.cg.fds.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.Customer;
import com.cg.fds.exception.InvalidCustomerException;
import com.cg.fds.repository.ICustomerRepository;

@ExtendWith(MockitoExtension.class)

public class CustomerServiceImpTest {
	@Mock
	ICustomerRepository customerRepository;

	@Spy
	@InjectMocks
	CustomerServiceImp customerService;
//	CustomerServiceImp service;
//	@BeforeEach
//	public void setUp() {
//		customerService = new CustomerServiceImp();
//	}

//	@Test
//	
//	public void testAdd1() {
//		Customer result = customerService.addCustomer(null);
//		Assertions.assertEquals(null, result);
//	}	
//	@Test
//		
//		public void testAdd2() {
//		Customer customer = new Customer();
//			Customer result = customerService.addCustomer(customer);
//			Assertions.assertNotNull(result);
//			Assertions.assertEquals(customer, result);
//		
//	}
	@Test

	public void addCustomerTest_1() {
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(customerRepository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.addCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}
	@Test

	public void removeCustomerTest_1() {
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(customerRepository.remove(customer)).thenReturn(customerSaved);
		Customer result = customerService.removeCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}

	@Test

	public void updateCustomerTest_1() {
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(customerRepository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.updateCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}

	@Test

	public void viewCustomerTest_1() {
		int id = 1;
		Customer customer = Mockito.mock(Customer.class);
		Optional<Customer> optionalSaved =Optional.of(customer);
		Mockito.when(customerRepository.findById(id)).thenReturn(optionalSaved);
		Customer result = customerService.viewCustomer(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer, result);

	}

	@Test
	public void validateCustomer_1() {
		Customer customer = null;
		Executable executable = () -> customerService.validateCustomer(customer);
		Assertions.assertThrows(InvalidCustomerException.class, executable);
	}

}
