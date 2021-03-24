package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.repository.IOrderRepository;

public class OrderServiceImp implements IOrderService{
	
	@Autowired
	private IOrderRepository orderRepository;

	@Override
	public OrderDetails addOrder(OrderDetails order) {
		validateOrder(order);
		return orderRepository.save(order);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		validateOrder(order);
		return orderRepository.save(order);
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		validateOrder(order); 
		return orderRepository.remove(order);
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) {
		validateOrder(order);
//		int orderId=order.getOrderId();
		Optional <OrderDetails> optionOrderDetail=orderRepository.findById(1);
		OrderDetails viewOrder=null;
		if(optionOrderDetail.isPresent())
		{
			viewOrder=optionOrderDetail.get();
		}
		return viewOrder;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant resName) {
//		List<OrderDetails> OrderList=orderRepository.findByResId(resName.get)
		
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void validateOrder(OrderDetails order) {
		if(order == null)
		{
			throw new InvalidOrderException("OrderDetail Cannot be null");
		}
		
	}

}
