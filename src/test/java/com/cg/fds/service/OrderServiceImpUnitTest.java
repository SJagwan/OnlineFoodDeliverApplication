package com.cg.fds.service;

import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Bill;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;

import com.cg.fds.exception.AddOrderException;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.exception.OrderNotFoundException;
import com.cg.fds.repository.IBillRepository;
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.IOrderRepository;
import com.cg.fds.util.BillUtil;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpUnitTest {

	@Mock
	IOrderRepository orderRepository;

	@Mock
	CartServiceImp cartService;
	@Mock
	BillServiceImp billService;
	
	@Mock
	private BillUtil billUtil;

	@Mock
	ICartItemRepository cartItemRepository;
	@Mock
	IBillRepository billRepository;

	@Spy
	@InjectMocks
	OrderServiceImp orderService;

	/**
	 * Scenario if OrderDetail is object; expect=object result = object
	 */

	@Test
	public void addOrderTest_1() {
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
		doNothing().when(orderService).validateOrder(orderDetail);
		List<Item> items = Mockito.mock(List.class);
		FoodCart cart = Mockito.mock(FoodCart.class);
		Mockito.when(orderDetail.getCart()).thenReturn(cart);
		Mockito.when(cartItemRepository.findItemsByCart(cart)).thenReturn(items);
		Mockito.when(items.isEmpty()).thenReturn(false);
		LocalDateTime currentTime = LocalDateTime.now();
		Mockito.doReturn(currentTime).when(orderService).currentDateTime();
		OrderDetails orderDetailSaved = Mockito.mock(OrderDetails.class);
		Mockito.when(orderRepository.save(orderDetail)).thenReturn(orderDetailSaved);
		Bill bill=Mockito.mock(Bill.class);
		Mockito.when(billUtil.getBill()).thenReturn(bill);
		Mockito.when(billService.addBill(bill)).thenReturn(bill);
		OrderDetails result = orderService.addOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result, orderDetailSaved);
		Mockito.verify(billUtil).getBill();
		Mockito.verify(orderRepository).save(orderDetail);
		Mockito.verify(orderService).validateOrder(orderDetail);
		Mockito.verify(orderDetail).setItems(items);
		Mockito.verify(orderDetail).setOrderDate(currentTime);
		Mockito.verify(orderDetail).setOrderStatus(OrderStatus.CREATED);
	}

	@Test
	public void addOrderTest_2() {
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
		doNothing().when(orderService).validateOrder(orderDetail);
		List<Item> items = Mockito.mock(List.class);
		FoodCart cart = Mockito.mock(FoodCart.class);
		Mockito.when(orderDetail.getCart()).thenReturn(cart);
		Mockito.when(cartItemRepository.findItemsByCart(cart)).thenReturn(items);
		Mockito.when(items.isEmpty()).thenReturn(true);
		Executable executable = () -> orderService.addOrder(orderDetail);
		Assertions.assertThrows(AddOrderException.class, executable);

	}

	/**
	 * Scenario if OrderDetail is object; expect=object result = object
	 */

	@Test
	public void updateOrderTest_1() {
		int id = 1;
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
//		Mockito.doNothing().when(orderService).validateOrder(orderDetail);
		OrderDetails orderDetailUpdated = Mockito.mock(OrderDetails.class);
		Mockito.when(orderDetail.getOrderId()).thenReturn(id);
		Mockito.when(orderRepository.existsById(id)).thenReturn(true);
		Mockito.when(orderRepository.save(orderDetail)).thenReturn(orderDetailUpdated);
		OrderDetails result = orderService.updateOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetailUpdated, result);
		Mockito.verify(orderRepository).existsById(1);
		Mockito.verify(orderRepository).save(orderDetail);
	}

	@Test
	public void updateOrderTest_2() {
		int id = 1;
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
		Mockito.doNothing().when(orderService).validateOrder(orderDetail);
		Mockito.when(orderDetail.getOrderId()).thenReturn(id);
		Mockito.when(orderRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> orderService.updateOrder(orderDetail);
		Assertions.assertThrows(OrderNotFoundException.class, executable);

	}

	/**
	 * Scenario if OrderDetail is object; expect=object result = object
	 */

	@Test
	public void removeOrderTest_1() {
		int id = 1;
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
		doNothing().when(orderService).validateOrder(orderDetail);
		Mockito.when(orderDetail.getOrderId()).thenReturn(id);
		Mockito.when(orderRepository.existsById(id)).thenReturn(true);
		Bill bill=Mockito.mock(Bill.class);
		Mockito.when(billRepository.findBillByOrder(orderDetail)).thenReturn(bill);
		OrderDetails result = orderService.removeOrder(orderDetail);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetail, result);
		Mockito.verify(orderRepository).existsById(1);
	}

	@Test
	public void removeOrderTest_2() {
		int id = 1;
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
		Mockito.doNothing().when(orderService).validateOrder(orderDetail);
		Mockito.when(orderDetail.getOrderId()).thenReturn(id);
		Mockito.when(orderRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> orderService.removeOrder(orderDetail);
		Assertions.assertThrows(OrderNotFoundException.class, executable);

	}

	/**
	 * Scenario if OrderDetail is object; expect=object result = object
	 */

	@Test
	public void viewOrderTest_1() {
		OrderDetails orderDetail = Mockito.mock(OrderDetails.class);
		int id = 1;
		Optional<OrderDetails> optionOrder = Optional.of(orderDetail);
		Mockito.when(orderRepository.findById(id)).thenReturn(optionOrder);
		OrderDetails result = orderService.viewOrder(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderDetail, result);
		Mockito.verify(orderRepository).findById(1);
	}

	@Test
	public void viewOrderTest_2() {
		int id = 1;
		Optional<OrderDetails> optionOrder = Optional.empty();
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

//	@Test
//	public void viewAllOrdersTest_Customer() {
//		List<OrderDetails>orderList=Mockito.mock(List.class);
//		Customer customer=Mockito.mock(Customer.class);
//		Mockito.when(orderRepository.findAllOrdersByCustomer(customer)).thenReturn(orderList);
//		List<OrderDetails> result=orderService.viewAllOrders(customer);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(orderList,result);		
//	}

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
