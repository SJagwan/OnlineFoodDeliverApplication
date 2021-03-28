package com.cg.fds.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Item;

public interface IItemRepository extends JpaRepository<Item,String> {
	 List<Item> findByitemName(String itemName);
	 
}
