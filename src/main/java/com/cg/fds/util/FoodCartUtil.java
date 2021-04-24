package com.cg.fds.util;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;


import org.springframework.stereotype.Component;

import com.cg.fds.dto.foodcart.FoodCartDetails;
import com.cg.fds.dto.foodcart.FoodCartItemDetails;
import com.cg.fds.entities.CartItem;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.ItemServiceImp;

@Component
public class FoodCartUtil {
	
	@Autowired
	private CartServiceImp cartService;
	
	@Autowired
	private ItemServiceImp itemService;
	
	
	
	public FoodCart getFoodCart() {
		return new FoodCart();
	}
	
	public FoodCartDetails toCartDetails(FoodCart cart)
	{
		FoodCartDetails fcd=new FoodCartDetails();
		fcd.setCustomerId(cart.getCustomer().getCustomerId());
		fcd.setFirstName(cart.getCustomer().getFirstName());
		List<CartItem>cartItems=cartService.findCartItemsByCart(cart);
		fcd.setItems(toFoodCartItemDeatilList(cartItems));
		return fcd;
	}
	
	public FoodCartItemDetails toFoodCartItemDeatil(CartItem cartItem)
	{
		FoodCartItemDetails foodItem=new FoodCartItemDetails();
		Item item=cartItem.getItem();
		foodItem.setItemId(item.getItemId());
		foodItem.setItemName(item.getItemName());
		foodItem.setQuantity(cartItem.getQuantity());
		foodItem.setCost(cartItem.getQuantity()*item.getCost());
		foodItem.setCategoryName(item.getCategory().getCategoryName());
		return foodItem;
	}
	
	public List<FoodCartItemDetails> toFoodCartItemDeatilList(List<CartItem>cartItems)
	{
		List<FoodCartItemDetails> list=new ArrayList<>();
		for(CartItem cartItem:cartItems)
		{
			list.add(toFoodCartItemDeatil(cartItem));
		}
		return list;
	}
	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}
}
