package com.cg.fds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;



public interface IOrderRepository {
	
	 OrderDetails save(OrderDetails order);
	 OrderDetails remove(OrderDetails order);
     Optional<OrderDetails> findById(int orderId);
     boolean existsById(int orderId);
//	public List<OrderDetails> findByResId(String restaurantId);
     
  // from OrderDetails order join order.cart cart where cart.customer=:customer
     List<OrderDetails> findAllOrdersByCustomer(Customer customer);


//	public OrderDetails addOrder(OrderDetails order);
//	public OrderDetails updateOrder(OrderDetails order);
//	public OrderDetails removeOrder(OrderDetails order);
//	public OrderDetails viewOrder(OrderDetails order);
//	public List<OrderDetails> viewAllOrders(Restaurant resName);
//	public List<OrderDetails> viewAllOrders(Customer customer);
	
}
