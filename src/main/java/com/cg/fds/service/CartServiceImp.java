package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.exception.CartNotExistException;
import com.cg.fds.exception.InvalidCartException;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.exception.RemoveRestaurantException;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.IItemRepository;

@Service
public class CartServiceImp implements ICartService {
	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private IItemRepository itemRepository;

	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) {
		validateCart(cart);
		List<Item> items = cart.getItemList();
		if (items == null) {
			items = new ArrayList<>();
			cart.setItemList(items);
		}
		items.add(item);
		return cartRepository.save(cart);
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, int quantity) {
		validateCart(cart);
		cartExist(cart);
		List<Item> items = cart.getItemList();
		if (!items.contains(item)) {
			throw new ItemNotFoundException(
					"Item is Not present in this ItemList for thisFoodCart=" + cart.getCartId());
		}
		item.setQuantity(quantity);
		itemRepository.save(item);
		return cartRepository.save(cart);
	}

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
		validateCart(cart);
		cartExist(cart);
		List<Item> items = cart.getItemList();
		if (!items.contains(item)) {
			throw new ItemNotFoundException("Item is Not present in the ItemList of Cart =" + cart.getCartId());
		}
		item.setQuantity(quantity);
		itemRepository.save(item);
		return cartRepository.save(cart);
	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) {
		validateCart(cart);
		cartExist(cart);
		List<Item> items = cart.getItemList();
		if (!items.contains(item)) {
			throw new ItemNotFoundException("Item is Not present in the ItemList of Cart =" + cart.getCartId());
		}
		items.remove(item);
		return cartRepository.save(cart);
	}

	@Override
	public FoodCart clearCart(FoodCart cart) {
		validateCart(cart);
		cartExist(cart);
		cart.setItemList(null);
		return cartRepository.save(cart);

	}

	public void validateCart(FoodCart cart) {
		if (cart == null) {
			throw new InvalidCartException("Cart cannot be null");
		}
	}

	public void cartExist(FoodCart cart) {
		String cartId =cart.getCartId();
		boolean exists = cartRepository.existsById(cartId);
		if (!exists) {
			throw new CartNotExistException("cart with id not present=" + cart.getCartId());
		}
	}

}
