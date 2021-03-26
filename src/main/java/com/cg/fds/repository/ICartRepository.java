package com.cg.fds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;

public interface ICartRepository extends JpaRepository<FoodCart,String>{
	
//	FoodCart save(FoodCart cart);
//	boolean existsById(String cartId);

//	public FoodCart addItemToCart(FoodCart cart,Item item);
//	public FoodCart increaseQuantity(FoodCart cart,Item item,int quantity);
//	public FoodCart reduceQuantity(FoodCart cart,Item item,int quantity);
//	public FoodCart removeItem(FoodCart cart,Item item);
//	public FoodCart clearCart(FoodCart cart);
	
}
