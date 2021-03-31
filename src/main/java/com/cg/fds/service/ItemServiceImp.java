package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidItemException;
import com.cg.fds.exception.InvalidItemIdException;
import com.cg.fds.exception.InvalidItemNameException;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.exception.RemoveItemException;
import com.cg.fds.exception.UpdateItemException;
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.IItemRepository;

@Service
public class ItemServiceImp implements IItemService {

	@Autowired
	private IItemRepository itemRepository;
	
	@Autowired
	private ICartItemRepository cartItemRepository;

	@Override
	public Item addItem(Item item) {
		validateItem(item);
		return itemRepository.save(item);
	}

	@Override
	public Item viewItem(String id) {
		Optional<Item> viewItem = itemRepository.findById(id);
		if (!viewItem.isPresent()) {
			throw new ItemNotFoundException("Item with id not present=" + id);
		}
		return viewItem.get();
	}

	@Override
	public Item updateItem(Item item) {
		validateItem(item);
		boolean exists = itemRepository.existsById(item.getItemId());
		if (!exists) {
			throw new UpdateItemException("Item with id not present=" + item.getItemId());
		}
		Item updateItem = itemRepository.save(item);
		return updateItem;
	}

	@Override
	public Item removeItem(String id) {
		boolean exists = itemRepository.existsById(id);
		if (!exists) {
			throw new RemoveItemException("Item with id not present=" + id);
		}
		Optional<Item> removeItem = itemRepository.findById(id);
		itemRepository.deleteById(id);
		return removeItem.get();
	}

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		return null;
	}

	@Override
	public List<Item> viewAllItems(Category cat) {
		return itemRepository.findByCategory(cat);
	}

	@Override
	public List<Item> viewAllItemsByName(String name) {
		List<Item> list = itemRepository.findByItemName(name);
		return list;
	}
	
	
	public List<Item> viewAllItemsByCart(FoodCart cart) {
		return cartItemRepository.findItemsByCart(cart);
	}

	/**
	 * Function to validate item
	 */

	void validateItem(Item item) {
		if (item == null) {
			throw new InvalidItemException("Item can't be null");
		}
		validateItemName(item.getItemName());
		validateItemId(item.getItemId());
	}

	/**
	 * Function to validate item Name
	 */
	void validateItemName(String itemName) {
		if (itemName == null || itemName.isEmpty() || itemName.trim().isEmpty()) {
			throw new InvalidItemNameException("Item Name can't be null or empty");
		}
	}

	/**
	 * Function to validate item ID
	 */
	void validateItemId(String itemId) {
		if (itemId == null || itemId.isEmpty() || itemId.trim().isEmpty()) {
			throw new InvalidItemIdException("Item Id can't be null or empty");
		}
	}
}
