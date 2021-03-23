package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Restaurant;

public interface IRestaurantService {

	public Restaurant addRestaurant(Restaurant res);
	public Restaurant removeRestaurant(Restaurant res);
	public Restaurant updateRestaurant(Restaurant res);
	public Restaurant viewRestaurant(String name);
	public Restaurant viewAllRestaurants();
	public List<Restaurant> viewNearByRestaurant(String location);
	public List<Restaurant> viewRestaurantByItemName(String name);
}
