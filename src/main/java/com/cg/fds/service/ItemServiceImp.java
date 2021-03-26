package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidItemException;
import com.cg.fds.exception.InvalidItemIdException;
import com.cg.fds.exception.InvalidItemNameException;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.exception.RemoveItemException;
import com.cg.fds.exception.UpdateItemException;
import com.cg.fds.repository.IItemRepository;

public class ItemServiceImp implements IItemService {

	@Autowired
	private IItemRepository itemRepository;

	@Override
	public Item addItem(Item item) {
		validateItem(item);
		Item addItem = itemRepository.save(item);
		return addItem;
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
		boolean exists = itemRepository.existsById("1");
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
		Item removeItem = itemRepository.remove(id);
		return removeItem;
	}

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItems(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItemsByName(String name) {
		List<Item> list = itemRepository.findItemByName(name);
		return list;
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
