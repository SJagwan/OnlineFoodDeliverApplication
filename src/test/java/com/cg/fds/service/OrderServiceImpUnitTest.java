package com.cg.fds.service;



import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.List;
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

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidCustomerException;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.exception.OrderNotFoundException;
import com.cg.fds.exception.RemoveOrderException;
import com.cg.fds.exception.UpdateOrderException;
import com.cg.fds.repository.IOrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpUnitTest {
	
	
	@Mock
	IOrderRepository orderRepository;
	
	@Spy
	@InjectMocks
	 OrderServiceImp orderService;

	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	
	@Test 
	public void addOrderTest() {
		OrderDetails orderDetail=Mockito.mock(OrderDetails.class);
		doNothing().when(orderService).validateOrder(orderDetail);
		LocalDateTime currentTime=LocalDateTime.now();
		Mockito.doReturn(currentTime).when(orderService).currentDateTime();
		OrderDetails orderDetailSaved=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.save(orderDetail)).thenReturn(orderDetailSaved);
		OrderDetails result=orderService.addOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result, orderDetailSaved);
		Mockito.verify(orderRepository).save(orderDetail);
		Mockito.verify(orderService).validateOrder(orderDetail);
		Mockito.verify(orderDetail).setOrderDate(currentTime);
		Mockito.verify(orderDetail).setOrderStatus(OrderStatus.CREATED);
	}
	
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	
	@Test
	public void updateOrderTest_1() {
		int id=1;
		OrderDetails orderDetail=Mockito.mock(OrderDetails.class);
//		Mockito.doNothing().when(orderService).validateOrder(orderDetail);
		OrderDetails orderDetailUpdated=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.existsById(id)).thenReturn(true);
		Mockito.when(orderRepository.save(orderDetail)).thenReturn(orderDetailUpdated);
		OrderDetails result=orderService.updateOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetailUpdated,result);
		Mockito.verify(orderRepository).existsById(1);
		Mockito.verify(orderRepository).save(orderDetail);
	}
	
	
	
	@Test
	public void updateOrderTest_2() {
		int id=1;
		OrderDetails orderDetail=Mockito.mock(OrderDetails.class);
		Mockito.doNothing().when(orderService).validateOrder(orderDetail);
		Mockito.when(orderRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> orderService.updateOrder(orderDetail);
		Assertions.assertThrows(UpdateOrderException.class, executable);
		
	}
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	@Test
	public void removeOrderTest_1() {
		int id=1;
		OrderDetails orderDetail=Mockito.mock(OrderDetails.class);
		doNothing().when(orderService).validateOrder(orderDetail);
		OrderDetails orderDetailRemoved=Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.existsById(id)).thenReturn(true);
		Mockito.when(orderRepository.remove(orderDetail)).thenReturn(orderDetailRemoved);
		OrderDetails result=orderService.removeOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetailRemoved,result);	
		Mockito.verify(orderRepository).existsById(1);
		Mockito.verify(orderRepository).remove(orderDetail);
	}
	
	@Test
	public void removeOrderTest_2() {
		OrderDetails orderDetail=Mockito.mock(OrderDetails.class);
		Mockito.doNothing().when(orderService).validateOrder(orderDetail);
		Mockito.when(orderRepository.existsById(1)).thenReturn(false);
		Executable executable = () -> orderService.removeOrder(orderDetail);
		Assertions.assertThrows(RemoveOrderException.class, executable);
			
	}
	
	/**
	 * Scenario if OrderDetail is object;
	 *  expect=object result = object
	 */
	
	@Test
	public void viewOrderTest_1() {
		OrderDetails orderDetail=Mockito.mock(OrderDetails.class);
		int id=1;
		Optional<OrderDetails>optionOrder=Optional.of(orderDetail);
		Mockito.when(orderRepository.findById(id)).thenReturn(optionOrder);
		OrderDetails result=orderService.viewOrder(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetail,result);
		Mockito.verify(orderRepository).findById(1);
	}
	
	@Test
	public void viewOrderTest_2() {
		int id=1;
		Optional<OrderDetails>optionOrder=Optional.empty();
		Mockito.when(orderRepository.findById(id)).thenReturn(optionOrder);
		Executable executable = () -> orderService.viewOrder(id);
		Assertions.assertThrows(OrderNotFoundException.class, executable);		
	}
	
//	@Test
//	public void viewAllOrdersTest_Restaurant() {
//		List<OrderDetails>orderList=Mockito.mock(List.class);
//		Restaurant restaurant=Mockito.mock(Restaurant.class);
//		Mockito.when(orderRepository.findByResId("1")).thenReturn(orderList);
//		List<OrderDetails> result=orderService.viewAllOrders(restaurant);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(orderList,result);		
//	}
	
	@Test
	public void viewAllOrdersTest_Customer() {
		List<OrderDetails>orderList=Mockito.mock(List.class);
		Customer customer=Mockito.mock(Customer.class);
		Mockito.when(orderRepository.findAllOrdersByCustomer(customer)).thenReturn(orderList);
		List<OrderDetails> result=orderService.viewAllOrders(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderList,result);		
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
