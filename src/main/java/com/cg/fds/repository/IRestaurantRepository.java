package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant, String> {

	List<Restaurant> findByRestaurantName(String name);

	List<Restaurant> findByAddress(Address address);
	
	Restaurant findRestaurantByAddress(Address address);

}
