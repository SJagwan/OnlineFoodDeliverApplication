package com.cg.fds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.IOrderRepository;

public class OrderServiceImp implements IOrderService{
	
	@Autowired
	private IOrderRepository orderRepository;

	@Override
	public OrderDetails addOrder(OrderDetails order) {
		return orderRepository.save(order);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant resName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
