package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.CartItem;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.exception.CartItemNotFoundException;
import com.cg.fds.exception.InvalidCartException;
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.ICartRepository;

@Service
public class CartServiceImp implements ICartService {
	
	private static final Logger Log=LoggerFactory.getLogger(CartServiceImp.class);
	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICartItemRepository cartItemRepository;

	/**
	 * scenario : Adding the items to the Cart input: Cart and Item Objects are
	 * passed in the parameter expectation: Items are added in the Cart
	 */

	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) {
		validateCart(cart);
		increaseQuantity(cart, item, 1);
		return cart;
	}

	/**
	 * scenario : Increasing the Quantity input: Cart, Item and quantity Objects are
	 * passed in the parameter expectation: Check the existing quantity of the cart,
	 * Add the updated quantity
	 */

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, int quantity) {
		Log.info("This function will increase the quantity.");
		validateCart(cart);
		String cartItemId = CartItem.id(cart, item);
		Optional<CartItem> optional = cartItemRepository.findById(cartItemId);
		if (!optional.isPresent()) {
			CartItem cartItem = new CartItem(cart, item, quantity);
			cartItemRepository.save(cartItem);
			return cart;
		}
		CartItem cartItem = optional.get();
		int existingQuantity = cartItem.getQuantity();
		int updatedQuantity = existingQuantity + quantity;
		cartItem.setQuantity(updatedQuantity);
		cartItemRepository.save(cartItem);
		return cart;
	}

	/**
	 * scenario : Reducing the Quantity input: Cart, Item and Quantity Objects are
	 * passed in the parameter expectation: Check the existing quantity of the cart,
	 * and reduce the quantity
	 */

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
		Log.info("This function will reduce the quantity, and if quantity is less than 0,remove it from cart");
		validateCart(cart);
		String cartItemId = CartItem.id(cart, item);
		Optional<CartItem> optional = cartItemRepository.findById(cartItemId);
		if (!optional.isPresent()) {
			throw new InvalidCartException("item not found in cart");
		}
		CartItem cartItem = optional.get();
		int existingQuantity = cartItem.getQuantity();
		int updatedQuantity = existingQuantity - quantity;
		if (updatedQuantity <= 0) {
			cartItemRepository.delete(cartItem);
			return cart;
		}
		cartItem.setQuantity(updatedQuantity);
		cartItemRepository.save(cartItem);
		return cart;
	}

	/**
	 * scenario : Remove the Item input: Cart and Item Objects are passed in the
	 * parameter expectation: Item gets removed
	 */
	@Override
	public FoodCart removeItem(FoodCart cart, Item item) {
		validateCart(cart);
		String cartItemId = CartItem.id(cart, item);
		cartItemRepository.deleteById(cartItemId);
		return cart;
	}

	/**
	 * scenario : Clearing the Cart input: Cart Object is passed in the parameter
	 * expectation: Items in the cart gets cleared
	 */
	@Override
	public FoodCart clearCart(FoodCart cart) {
		validateCart(cart);
		cartItemRepository.deleteByCart(cart);
		return cart;
	}

	/**
	 * scenario : Viewing the Cart input: Customer Id Object is passed in the
	 * parameter expectation: Viewing the Cart by customer Id
	 */

	@Override
	public FoodCart findFoodCartByCustomer(String customerId) {
		Customer customer = customerService.viewCustomer(customerId);
		FoodCart foodCart = cartRepository.findFoodCartByCustomer(customer);
		return foodCart;
	}
	
	@Override
	public List<CartItem> findCartItemsByCart(FoodCart cart){
		return cartItemRepository.findByCart(cart);
	}
	
	@Override
	public CartItem findCartItem(FoodCart cart, Item item){
		String id=CartItem.id(cart,item);
		Optional<CartItem> optional=cartItemRepository.findById(id);
	    if(!optional.isPresent()){
	    	throw new CartItemNotFoundException("cart item not found");
		}
	    return optional.get();
	}

	public void validateCart(FoodCart cart) {
		if (cart == null) {
			throw new InvalidCartException("Cart cannot be null");
		}
	}

}
