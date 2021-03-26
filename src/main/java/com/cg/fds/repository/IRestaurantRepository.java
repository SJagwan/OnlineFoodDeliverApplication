package com.cg.fds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Restaurant;

public interface IRestaurantRepository {
	public Restaurant save(Restaurant restaurant);

	public List<Restaurant> findByLocation(String Location);

	public List<Restaurant> findRestaurantByItemName(String name);

	public Restaurant remove(Restaurant restaurant);

	public Optional<Restaurant> findById(String id);
	
	boolean existsById(String restaurantId);

}
