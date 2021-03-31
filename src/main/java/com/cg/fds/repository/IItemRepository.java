package com.cg.fds.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

public interface IItemRepository extends JpaRepository<Item,String> {

	 List<Item> findByitemName(String itemName);
	 List<Item> findByCategory(Category category);
	 //List<Item> findByRestaurant(Restaurant restaurant);
}
