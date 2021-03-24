package com.cg.fds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.OrderDetails;



public interface IOrderRepository {
	
	public OrderDetails save(OrderDetails order);
	public OrderDetails remove(OrderDetails order);
    public Optional<OrderDetails> findById(int orderId);
	public List<OrderDetails> findByResId(String restaurantId);

//	public OrderDetails addOrder(OrderDetails order);
//	public OrderDetails updateOrder(OrderDetails order);
//	public OrderDetails removeOrder(OrderDetails order);
//	public OrderDetails viewOrder(OrderDetails order);
//	public List<OrderDetails> viewAllOrders(Restaurant resName);
//	public List<OrderDetails> viewAllOrders(Customer customer);
	
}
