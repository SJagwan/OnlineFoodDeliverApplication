package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidRestaurantException;
import com.cg.fds.exception.InvalidRestaurantLocationException;
import com.cg.fds.exception.InvalidRestaurantNameException;
import com.cg.fds.exception.RemoveRestaurantException;
import com.cg.fds.exception.RestaurantNotFoundException;
import com.cg.fds.exception.UpdateRestaurantException;
import com.cg.fds.repository.IAddressRepository;
import com.cg.fds.repository.IRestaurantRepository;

@Service
public class RestaurantServiceImp implements IRestaurantService {
	@Autowired
	private IRestaurantRepository restaurantRepository;
	
	@Autowired
	private IAddressRepository addressRepository;
	/**
	 * scenario : Adding the Restaurant
	 * input: res Object is passed in the parameter
	 * expectation: Restaurant should be added
	 */

	@Override
	public Restaurant addRestaurant(Restaurant res) {
		validateRestaurant(res);
		Address address=res.getAddress();
		addressRepository.save(address);
		Restaurant addRestaurant = restaurantRepository.save(res);
		return addRestaurant;
	}
	/**
	 * scenario : Removing the restaurant
	 * input: res Object is passed in the parameter
	 * expectation: If the restaurant is present in the Database, then restaurant is getting removed, or else an exception is thrown
	 */

	@Override
	public Restaurant removeRestaurant(Restaurant res) {
		validateRestaurant(res);
		String restaurantId = res.getRestaurantId();
		boolean exists = restaurantRepository.existsById( restaurantId );
		if (!exists) {
			throw new RemoveRestaurantException("Restaurant with id not present=" + res.getRestaurantId());
		}
		restaurantRepository.delete(res);
		return res;
	}
	/**
	 * scenario : Updating the restaurant
	 * input: res Object is passed in the parameter
	 * expectation: If the restaurant is present in the Database, then restaurant is getting updated, or else an exception is thrown
	 */

	@Override
	public Restaurant updateRestaurant(Restaurant res) {
		validateRestaurant(res);
		String restaurantId = res.getRestaurantId();
		boolean exists = restaurantRepository.existsById( restaurantId );
		if (!exists) {
			throw new UpdateRestaurantException("Restaurant with id not present=" + res.getRestaurantId());
		}
		Restaurant updaterestaurant = restaurantRepository.save(res);
		return updaterestaurant;
	}

	/**
	 * scenario : viewing the restaurant
	 * input: id Object is passed in the parameter
	 * expectation: If the Restaurant is present in the Database, then restaurant is getting viewed, or else an exception is thrown
	 */
	@Override
	public Restaurant viewRestaurant(String id) {
		Optional<Restaurant> viewRestaurant = restaurantRepository.findById(id);
		if (!viewRestaurant.isPresent()) {
			throw new RestaurantNotFoundException("Restaurant with id not present=" + id);
		}
		return viewRestaurant.get();
	}
	/**
	 * scenario : viewing all the restaurants
	 */

	@Override
	public Restaurant viewAllRestaurants() {
		return null;
	}
	/**
	 * scenario : viewing the list of all near by restaurants
	 * input: location Object is passed in the parameter
	 * expectation: List of restaurants should be returned with respect to the address/location
	 */
	@Override
	public List<Restaurant> viewNearByRestaurant(String location) {

		Address address=addressRepository.findAddressByArea(location);
		List<Restaurant> listr=restaurantRepository.findByAddress(address);
		return listr;
	}
	/**
	 * scenario : viewing the list of restaurant by the ItemName
	 * input: name Object is passed in the parameter
	 * expectation: List of restaurant should be returned
	 */

	@Override
	public List<Restaurant> viewRestaurantByItemName(String name) {

		List<Restaurant> list=restaurantRepository.findByRestaurantName(name);
		return list;
	}

	void validateRestaurantName(String name) {
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			throw new InvalidRestaurantNameException("Restaurant Name can't be null or empty");
		}
	}

	void validateRestaurant(Restaurant restaurant) {
		if (restaurant == null) {
			throw new InvalidRestaurantException("Restaurant can't be null ");
		}

		validateRestaurantName(restaurant.getRestaurantName());
		validateRestaurantLocation(restaurant.getAddress().getArea());
	}

	void validateRestaurantLocation(String location) {
		if (location == null || location.isEmpty() || location.trim().isEmpty()) {
			throw new InvalidRestaurantLocationException("Restaurant location  can't be null or empty");
		}

	}

}
