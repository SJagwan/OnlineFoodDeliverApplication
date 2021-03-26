package com.cg.fds.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant,String>{
	

// List<Restaurant> findByLocation(String Location);
//
// List<Restaurant> findRestaurantByItemName(String name);


}
