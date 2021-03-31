package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ItemServiceImp implements IItemService {

	@Autowired
	private IItemRepository itemRepository;
	/**
	 * scenario : Adding the items
	 * input: item Object is passed in the parameter
	 * expectation: Items should be added
	 */

	@Override
	public Item addItem(Item item) {
		validateItem(item);
		return itemRepository.save(item);
	}
	/**
	 * scenario : viewing the items
	 * input: id Object is passed in the parameter
	 * expectation: If the item is present in the Database, then item is getting viewed, or else an exception is thrown
	 */
	@Override
	public Item viewItem(String id) {
		Optional<Item> viewItem = itemRepository.findById(id);
		if (!viewItem.isPresent()) {
			throw new ItemNotFoundException("Item with id not present=" + id);
		}
		return viewItem.get();
	}
	/**
	 * scenario : Updating the item
	 * input: item Object is passed in the parameter
	 * expectation: If the item is present in the Database, then item is getting updated, or else an exception is thrown
	 */

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
	/**
	 * scenario : Removing the Bill
	 * input: id Object is passed in the parameter
	 * expectation: If the item is present in the Database, then item is getting removed, or else an exception is thrown
	 */
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
	/**
	 * scenario : viewing the list of all items in the restaurant
	 * input: res Object is passed in the parameter
	 * expectation: List should be viewed
	 */

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		
		return null;
	}
	/**
	 * scenario : viewing the list of all items in the category
	 * input: cat Object is passed in the parameter
	 * expectation: List should be viewed
	 */

	@Override
	public List<Item> viewAllItems(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * scenario : viewing the list of all items by name and returning the list
	 * input: name Object is passed in the parameter
	 * expectation: List shoukd be returned
	 */

	@Override
	public List<Item> viewAllItemsByName(String name) {
		List<Item> list = itemRepository.findByItemName(name);
		return list;
	}

	/**
	 * scenario : Validate the item
	 * input: item Object is passed in the parameter
	 * expectation: If the item is null, an exception is thrown
	 */

	void validateItem(Item item) {
		if (item == null) {
			throw new InvalidItemException("Item can't be null");
		}
		validateItemName(item.getItemName());
		validateItemId(item.getItemId());
	}

	/**
	 * scenario : Validating the Item name
	 * input: itemName Object is passed in the parameter
	 * expectation: If the itemName is null, an exception is thrown
	 */
	void validateItemName(String itemName) {
		if (itemName == null || itemName.isEmpty() || itemName.trim().isEmpty()) {
			throw new InvalidItemNameException("Item Name can't be null or empty");
		}
	}
	/**
	 * scenario : Validating the item Id
	 * input: itemId Object is passed in the parameter
	 * expectation: If the itemId is null, an exception is thrown
	 */
	void validateItemId(String itemId) {
		if (itemId == null || itemId.isEmpty() || itemId.trim().isEmpty()) {
			throw new InvalidItemIdException("Item Id can't be null or empty");
		}
	}
}
