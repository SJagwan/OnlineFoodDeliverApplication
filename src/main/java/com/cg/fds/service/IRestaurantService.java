package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Restaurant;

public interface IRestaurantService {

	 Restaurant addRestaurant(Restaurant res);
	 Restaurant removeRestaurant(Restaurant res);
	 Restaurant updateRestaurant(Restaurant res);
	 Restaurant viewRestaurant(String name);
	 List<Restaurant> viewAllRestaurants();
	 List<Restaurant> viewNearByRestaurant(String location);
	 List<Restaurant> viewRestaurantByItemName(String name);
}
