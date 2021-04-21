package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

public interface IItemService {

	 Item addItem(Item item);
	 Item viewItem(String id);
	 Item updateItem(Item item);
	 Item removeItem(String id);
	 List<Item> viewAllItems(Restaurant res);
	 List<Item> viewAllItems(Category cat);
	 List<Item> viewAllItemsByName(String name);
	
}
