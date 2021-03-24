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
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.InvalidCustomerException;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.repository.IOrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpUnitTest {
	
	
	@Mock
	IOrderRepository orderRepository;
	
	@Spy
	@InjectMocks
	 OrderServiceImp orderService;

	OrderDetails orderDetail;
	@BeforeEach
	public void setUp() {
		orderDetail=Mockito.mock(OrderDetails.class);
	}
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	
	@Test 
	public void addOrderTest() {
		OrderDetails orderDetailSaved=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.save(orderDetail)).thenReturn(orderDetailSaved);
		OrderDetails result=orderService.addOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result, orderDetailSaved);
	}
	
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	
	@Test
	public void updateOrderTest() {
		OrderDetails orderDetailUpdated=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.save(orderDetail)).thenReturn(orderDetailUpdated);
		OrderDetails result=orderService.updateOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetailUpdated,result);		
	}
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	@Test
	public void removeOrderTest() {
		OrderDetails orderDetailRemoved=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.remove(orderDetail)).thenReturn(orderDetailRemoved);
		OrderDetails result=orderService.removeOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetailRemoved,result);		
	}
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	@Test
	public void viewOrderTest() {
		Optional<OrderDetails>optionOrder=Optional.of(orderDetail);
		Mockito.when(orderRepository.findById(1)).thenReturn(optionOrder);
		OrderDetails result=orderService.viewOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetail,result);		
	}
	
	/**
	 * Scenario if OrderDetail is null;
	 * 
	 */
	
	@Test
	public void validateOrderTest() {
		OrderDetails order = null;
		Executable executable = () -> orderService.validateOrder(order);
		Assertions.assertThrows(InvalidOrderException.class, executable);
	}


}
