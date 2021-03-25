package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.repository.ICartRepository;

public class CartServiceImp implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) {
    List <Item> items = cart.getItemList();
    if(items==null) {
    	items = new ArrayList<>();
    	cart.setItemList(items);
    }
    items.add(item);
		cart.setItemList(items);
		
		return cartRepository.save(cart);
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart clearCart(FoodCart cart) {
		// TODO Auto-generated method stub
		return null;
	}

}
