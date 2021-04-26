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
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.IItemRepository;
import com.cg.fds.repository.IRestaurantRepository;

@Service
public class ItemServiceImp implements IItemService {

	@Autowired
	private IItemRepository itemRepository;

	@Autowired
	private ICartItemRepository cartItemRepository;

	@Autowired
	private IRestaurantRepository restaurantRepository;

	/**
	 * scenario : Adding the items input: item Object is passed in the parameter
	 * expectation: Items should be added
	 */

	@Override
	public Item addItem(Item item) {
		validateItem(item);
		Item saved = itemRepository.save(item);
		List<Restaurant> restaurants = saved.getRestaurants();
		if (restaurants != null) {
			for (Restaurant restaurant : restaurants) {
				List<Item> restaurantItems = restaurant.getItemList();
				if (!restaurantItems.contains(item)) {
					restaurantItems.add(item);
					restaurantRepository.save(restaurant);
				}
			}
		}
		return saved;
	}

	/**
	 * scenario : viewing the items input: id Object is passed in the parameter
	 * expectation: If the item is present in the Database, then item is getting
	 * viewed, or else an exception is thrown
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
	 * scenario : Updating the item input: item Object is passed in the parameter
	 * expectation: If the item is present in the Database, then item is getting
	 * updated, or else an exception is thrown
	 */

	@Override
	public Item updateItem(Item item) {
		validateItem(item);
		boolean exists = itemRepository.existsById(item.getItemId());
		if (!exists) {
			throw new ItemNotFoundException("Item with id not present=" + item.getItemId());
		}
		Item updateItem = itemRepository.save(item);
		return updateItem;
	}

	/**
	 * scenario : Removing the Bill input: id Object is passed in the parameter
	 * expectation: If the item is present in the Database, then item is getting
	 * removed, or else an exception is thrown
	 */
	@Override
	public Item removeItem(String id) {
		boolean exists = itemRepository.existsById(id);
		if (!exists) {
			throw new ItemNotFoundException("Item with id not present=" + id);
		}
		Optional<Item> removeItem = itemRepository.findById(id);
		Item item= removeItem.get();
		List<Restaurant>restaurants=item.getRestaurants();
		for(Restaurant restaurant:restaurants)
		{
			List<Item>items=restaurant.getItemList();
			if(items.contains(item))
			{
				items.remove(item);
			}
			restaurantRepository.save(restaurant);
		}
		
		itemRepository.deleteById(id);
		return removeItem.get();
	}

	/**
	 * scenario : viewing the list of all items in the restaurant input: res Object
	 * is passed in the parameter expectation: List should be viewed
	 */

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		List<Item> list = res.getItemList();
		return list;

	}

	/**
	 * scenario : viewing the list of all items in the category input: cat Object is
	 * passed in the parameter expectation: List should be viewed
	 */

	@Override
	public List<Item> viewAllItems(Category cat) {
		return itemRepository.findByCategory(cat);
	}

	/**
	 * scenario : viewing the list of all items by name and returning the list
	 * input: name Object is passed in the parameter expectation: List should be
	 * returned
	 */

	@Override
	public List<Item> viewAllItemsByName(String name) {
		List<Item> list = itemRepository.findByItemName(name);
		if(list.isEmpty())
		{
			throw new InvalidItemNameException("Item Name is does exist");
		}
		return list;
	}

	public List<Item> viewAllItemsByCart(FoodCart cart) {
		return cartItemRepository.findItemsByCart(cart);
	}
	
	@Override
	public List<Item> viewAllItem(){
		return itemRepository.findAll();
	}

	/**
	 * scenario : Validate the item input: item Object is passed in the parameter
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
	 * scenario : Validating the Item name input: itemName Object is passed in the
	 * parameter expectation: If the itemName is null, an exception is thrown
	 */
	void validateItemName(String itemName) {
		if (itemName == null || itemName.isEmpty() || itemName.trim().isEmpty()) {
			throw new InvalidItemNameException("Item Name can't be null or empty");
		}
	}

	/**
	 * scenario : Validating the item Id input: itemId Object is passed in the
	 * parameter expectation: If the itemId is null, an exception is thrown
	 */
	void validateItemId(String itemId) {
		if (itemId == null || itemId.isEmpty() || itemId.trim().isEmpty()) {
			throw new InvalidItemIdException("Item Id can't be null or empty");
		}
	}
}
