package com.cg.fds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidRestaurantException;
import com.cg.fds.exception.InvalidRestaurantLocationException;
import com.cg.fds.exception.InvalidRestaurantNameException;
import com.cg.fds.repository.IRestaurantRepository;

public class RestaurantServiceImp implements IRestaurantService {
	@Autowired
	private IRestaurantRepository restaurantRepository;

	@Override
	public Restaurant addRestaurant(Restaurant res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant removeRestaurant(Restaurant res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res) {

		Restaurant updaterestaurant = restaurantRepository.save(res);
		return updaterestaurant;
	}

	@Override
	public Restaurant viewRestaurant(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant viewAllRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) {
		List<Restaurant> restaurants = restaurantRepository.findByLocation(location);
		return restaurants;
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String name) {
		validateRestaurantName(name);
		List<Restaurant> restaurants = restaurantRepository.findRestaurantByItemName(name);
		return restaurants;
	}

	void validateRestaurantName(String name) {
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			throw new InvalidRestaurantNameException("Restaurant Name can't be null or empty");
		}
	}

	void validateRestaurant(Restaurant restaurant) {
            if (restaurant == null) {
                throw new InvalidRestaurantException("Restaurant can't be null ");
            }    }
	
    void validateRestaurantLocation(String location){
    	if (location == null || location.isEmpty() || location.trim().isEmpty()) 
    	{
        throw new InvalidRestaurantLocationException("Restaurant Name can't be null or empty");
        }
   
    
            
        
        
    }

}
