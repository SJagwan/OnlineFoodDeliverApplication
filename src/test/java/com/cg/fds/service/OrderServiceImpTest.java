package com.cg.fds.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.OrderDetails;
import com.cg.fds.repository.IOrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpTest {
	
	
	
	
	@Mock
	IOrderRepository orderRepository;
	
	@Spy
	@InjectMocks
	OrderServiceImp orderService;
	
//	void setUp() {
//		orderRepository=Mockito.mock(IOrderRepository.class);
//	}
	
	/**
	 * Scenario if OrderDetail is null;
	 *  expect=null result = null
	 */
	
//	@Test
//	public void addOrderTest_1() {
//		OrderDetails result=orderService.addOrder(null);
//		Assertions.assertEquals(null, result);	
//	}
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
//	@Test
//	public void addOrderTest_2() {
//		OrderDetails orderDetail=new OrderDetails();
//		OrderDetails result=orderService.addOrder(orderDetail);
//		Assertions.assertEquals(orderDetail, result);	
//	}
	
	
	
	@Test 
	void addOrderTest_2() {
		OrderDetails orderDetail=new OrderDetails();
		orderDetail.setOrderDate(LocalDateTime.now());
		
		OrderDetails orderDetailMock=Mockito.mock(OrderDetails.class);
		OrderDetails orderDetailSaved=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.save(orderDetailMock)).thenReturn(orderDetailSaved);
		OrderDetails result=orderService.addOrder(orderDetail);
		System.out.print(result);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result, orderDetailSaved);
	}

}
