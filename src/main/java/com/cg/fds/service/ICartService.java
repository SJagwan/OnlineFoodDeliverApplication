package com.cg.fds.service;

import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;

public interface ICartService {

	 FoodCart addItemToCart(FoodCart cart,Item item);
	 FoodCart increaseQuantity(FoodCart cart,Item item,int quantity);
	 FoodCart reduceQuantity(FoodCart cart,Item item,int quantity);
	 FoodCart removeItem(FoodCart cart,Item item);
	 FoodCart clearCart(FoodCart cart);
	 FoodCart findFoodCartByCustomer(String customerId);
}
