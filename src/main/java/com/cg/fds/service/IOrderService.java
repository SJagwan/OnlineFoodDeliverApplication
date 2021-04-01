package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;

public interface IOrderService {

	 OrderDetails addOrder(OrderDetails order);
	 OrderDetails updateOrder(OrderDetails order);
	 OrderDetails removeOrder(OrderDetails order);
	 OrderDetails viewOrder(int orderId);
	 List<OrderDetails> viewAllOrders(Restaurant resName);
	 List<OrderDetails> viewAllOrders(Customer customer);
	
}
