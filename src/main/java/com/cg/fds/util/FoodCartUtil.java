package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.FoodCart;

@Component
public class FoodCartUtil {
	
	public FoodCart getFoodCart() {
		return new FoodCart();
	}

}
