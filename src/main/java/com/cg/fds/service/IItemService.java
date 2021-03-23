package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

public interface IItemService {

	public Item addItem(Item item);
	public Item viewItem(String id);
	public Item updateItem(Item item);
	public Item removeItem(String id);
	public List<Item> viewAllItems(Restaurant res);
	public List<Item> viewAllItems(Category cat);
	public List<Item> viewAllItemsByName(String name);
	
}
