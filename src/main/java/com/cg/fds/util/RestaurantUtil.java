package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Restaurant;

@Component
public class RestaurantUtil {
	
	public Restaurant getRestaurant() {
		return new Restaurant();
	}

}
