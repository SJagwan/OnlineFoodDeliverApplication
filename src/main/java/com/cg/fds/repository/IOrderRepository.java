package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;

public interface IOrderRepository extends JpaRepository<OrderDetails, Integer> {

	OrderDetails findOrderDetailsByCart(FoodCart cart);

	List<OrderDetails> findByCart(FoodCart cart);

}
