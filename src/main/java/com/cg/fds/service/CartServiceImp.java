package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.CartItem;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.exception.CartException;
import com.cg.fds.exception.CartNotExistException;
import com.cg.fds.exception.InvalidCartException;
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.IItemRepository;

@Service
public class CartServiceImp implements ICartService {
	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IItemRepository itemRepository;

	@Autowired
	private ICartItemRepository cartItemRepository;

	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) {
		validateCart(cart);
		increaseQuantity(cart,item,1);
		return cart;
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, int quantity) {
		validateCart(cart);
		cartExist(cart);
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

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
	    validateCart(cart);
        String cartItemId = CartItem.id(cart, item);
        Optional<CartItem> optional = cartItemRepository.findById(cartItemId);
        if (!optional.isPresent()) {
            throw new CartException("item not found in cart");
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

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) {
		validateCart(cart);
		cartExist(cart);
        String cartItemId = CartItem.id(cart, item);
        cartItemRepository.deleteById(cartItemId);
        return cart;
	}

	@Override
	public FoodCart clearCart(FoodCart cart) {
		validateCart(cart);
		cartExist(cart);
        cartItemRepository.deleteByCart(cart);
        return cart;
	}

	@Override
	public FoodCart findFoodCartByCustomer(String customerId) {
		Customer customer = customerService.viewCustomer(customerId);
		FoodCart foodCart = cartRepository.findFoodCartByCustomer(customer);
		return foodCart;
	}

	public void validateCart(FoodCart cart) {
		if (cart == null) {
			throw new InvalidCartException("Cart cannot be null");
		}
	}

	public void cartExist(FoodCart cart) {
		String cartId = cart.getCartId();
		boolean exists = cartRepository.existsById(cartId);
		if (!exists) {
			throw new CartNotExistException("cart with id not present=" + cart.getCartId());
		}
	}

}
