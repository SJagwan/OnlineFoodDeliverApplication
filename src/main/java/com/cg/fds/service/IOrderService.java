package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;

public interface IOrderService {

	public OrderDetails addOrder(OrderDetails order);
	public OrderDetails updateOrder(OrderDetails order);
	public OrderDetails removeOrder(OrderDetails order);
	public OrderDetails viewOrder(OrderDetails order);
	public List<OrderDetails> viewAllOrders(Restaurant resName);
	public List<OrderDetails> viewAllOrders(Customer customer);
	
}
