package com.cg.fds.util;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;


import org.springframework.stereotype.Component;

import com.cg.fds.dto.foodcart.FoodCartDetails;
import com.cg.fds.dto.foodcart.FoodCartItemDetails;
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
		FoodCart c=cartService.findFoodCartByCustomer(cart.getCustomer().getCustomerId());
		List<Item>items=itemService.viewAllItemsByCart(c);	
		fcd.setItems(toFoodCartItemDeatilList(items));
		return fcd;
	}
	
	public FoodCartItemDetails toFoodCartItemDeatil(Item item)
	{
		FoodCartItemDetails foodItem=new FoodCartItemDetails();
		foodItem.setItemId(item.getItemId());
		foodItem.setItemName(item.getItemName());
//		foodItem.setQuantity(item.getQuantity());
		foodItem.setCost(item.getCost());
		foodItem.setCategoryName(item.getCategory().getCategoryName());
		return foodItem;
	}
	
	public List<FoodCartItemDetails> toFoodCartItemDeatilList(List<Item>items)
	{
		List<FoodCartItemDetails> list=new ArrayList<>();
		for(Item item:items)
		{
			list.add(toFoodCartItemDeatil(item));
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
