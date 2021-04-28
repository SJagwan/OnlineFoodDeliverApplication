package com.cg.fds.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.CartItem;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.exception.InvalidCartException;

import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.IItemRepository;

@ExtendWith(MockitoExtension.class)
public class CartServiceImpUnitTest {

	@Mock
	ICartRepository cartRepository;

	@Mock
	IItemRepository itemRepository;

	@Mock
	ICartItemRepository cartItemRepository;

	@Spy
	@InjectMocks
	CartServiceImp cartService;

	/**
	 * scenario when items exist in cart before , items list is not null
	 */
	@Test
	void addItemToCartTest_1() {
		FoodCart cart = Mockito.mock(FoodCart.class);
		Item item = Mockito.mock(Item.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doReturn(cart).when(cartService).increaseQuantity(cart, item, 1);
		FoodCart result = cartService.addItemToCart(cart, item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).increaseQuantity(cart, item, 1);
		Mockito.verify(cartService).validateCart(cart);
	}

	/**
	 * scenario , cart is null
	 */

	@Test
	void addItemToCartTest_2() {
		FoodCart cart = Mockito.mock(FoodCart.class);
		Mockito.doThrow(InvalidCartException.class).when(cartService).validateCart(cart);
		Executable executable = () -> cartService.validateCart(cart);
		Assertions.assertThrows(InvalidCartException.class, executable);

	}



	@Test
	void removeTest_1() {
		String cartItemId = "string";
		FoodCart cart = Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
//		CartItem cartItem=Mockito.mock(CartItem.class);
		Item item = Mockito.mock(Item.class);
		Mockito.when(CartItem.id(cart, item)).thenReturn(cartItemId);
		FoodCart result = cartService.removeItem(cart, item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).validateCart(cart);
	}

	@Test
	void clearCartTest_1() {
		FoodCart cart = Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		FoodCart result = cartService.clearCart(cart);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).validateCart(cart);

	}

	@Test
	public void validateCartTest() {
		FoodCart cart = null;
		Executable executable = () -> cartService.validateCart(cart);
		Assertions.assertThrows(InvalidCartException.class, executable);
	}

}
