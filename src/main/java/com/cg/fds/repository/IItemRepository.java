package com.cg.fds.repository;

import java.util.List;
import java.util.Optional;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;

public interface IItemRepository {
	 Item save(Item item);

	//public List<Item> findByLocation(String Location);

	//public List<Item> findRestaurantByItemName(String name);

	 Item remove(String itemid);
	 Optional<Item> findById(String itemid);
	
	boolean existsById(String itemId);

	
	/*	public Item addItem(Item item);
	public Item viewItem(String id);
	public Item updateItem(Item item);
	public Item removeItem(String id);
	public List<Item> viewAllItems(Restaurant res);
	public List<Item> viewAllItems(Category cat);
	public List<Item> viewAllItemsByName(String name);
	*/
}
