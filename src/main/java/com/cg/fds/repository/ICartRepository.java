package com.cg.fds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;

public interface ICartRepository extends JpaRepository<FoodCart, String> {
	FoodCart findFoodCartByCustomer(Customer customer);

}
