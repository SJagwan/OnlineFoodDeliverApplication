package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.fds.dto.restaurant.RestaurantDetails;
import com.cg.fds.entities.Restaurant;

@Component
public class RestaurantUtil {
	
	public Restaurant getRestaurant() {
		return new Restaurant();
	}
	
	public RestaurantDetails toRestaurantDetails(Restaurant res) {
		RestaurantDetails resNew = new RestaurantDetails();
		resNew.setContactNumber(res.getContactNumber());
		resNew.setManagerName(res.getManagerName());
		resNew.setRestaurantName(res.getRestaurantName());
		return resNew;
		}
	
	public List<RestaurantDetails> toRestaurantDetailsList(List<Restaurant> res){
		List<RestaurantDetails> restauarntDetails = new ArrayList<>();
		for(Restaurant r : res) {
			restauarntDetails.add(toRestaurantDetails(r));
		}
		return restauarntDetails;
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
