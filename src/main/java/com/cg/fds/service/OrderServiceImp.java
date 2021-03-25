package com.cg.fds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.exception.OrderNotFoundException;
import com.cg.fds.exception.RemoveOrderException;
import com.cg.fds.exception.UpdateOrderException;
import com.cg.fds.repository.IOrderRepository;

public class OrderServiceImp implements IOrderService{
	
	@Autowired
	private IOrderRepository orderRepository;
	
	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}

	@Override
	public OrderDetails addOrder(OrderDetails order) {
		validateOrder(order);
//		FoodCart cart = order.getCart();
//        List<Item> items = cart.getItemList();
//        if (items == null || items.isEmpty()) {
//            throw new AddOrderException("order can't be created because cart is empty");
//        }
//        order.setItems(items);
        order.setOrderDate(currentDateTime());
        order.setOrderStatus(OrderStatus.CREATED);
		return orderRepository.save(order);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		validateOrder(order);
		int orderId=order.getOrderId();
		boolean exist=orderRepository.existsById(1);
		if(!exist)
		{
			throw new UpdateOrderException("Order doesn't exist for id =" + order.getOrderId());
		}
		return orderRepository.save(order);
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		validateOrder(order); 
//		int orderId=order.getOrderId();
		boolean exist=orderRepository.existsById(1);
		if(!exist)
		{
			throw new RemoveOrderException("Order doesn't exist for id =" + order.getOrderId());
		}
		return orderRepository.remove(order);
	}

	@Override
	public OrderDetails viewOrder(int orderId) {
		Optional <OrderDetails> optionOrderDetail=orderRepository.findById(orderId);
		if(!optionOrderDetail.isPresent())
		{
			throw new OrderNotFoundException("order not found for id=" + orderId);
		}
		return optionOrderDetail.get();
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant resName) {
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
//		String customerId=customer.getCustomerId();
		List<OrderDetails> orderList=orderRepository.findAllOrdersByCustomer(customer);
		return orderList;
	}
	
	public void validateOrder(OrderDetails order) {
		if(order == null)
		{
			throw new InvalidOrderException("OrderDetail Cannot be null");
		}
		
	}


}
