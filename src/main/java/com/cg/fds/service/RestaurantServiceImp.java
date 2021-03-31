package com.cg.fds.service;

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

	@Override
	public Restaurant addRestaurant(Restaurant res) {
		validateRestaurant(res);
		Address address=res.getAddress();
		addressRepository.save(address);
		Restaurant addRestaurant = restaurantRepository.save(res);
		return addRestaurant;
	}

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

	@Override
	public Restaurant viewRestaurant(String id) {
		Optional<Restaurant> viewRestaurant = restaurantRepository.findById(id);
		if (!viewRestaurant.isPresent()) {
			throw new RestaurantNotFoundException("Restaurant with id not present=" + id);
		}
		return viewRestaurant.get();
	}

	@Override
	public List<Restaurant> viewAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) {
		Address address=addressRepository.findAddressByArea(location);
		List<Restaurant> listr=restaurantRepository.findByAddress(address);
		return listr;
	} 

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
