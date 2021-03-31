package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;
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
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.CartNotExistException;
import com.cg.fds.exception.InvalidCartException;
import com.cg.fds.exception.InvalidRestaurantLocationException;
import com.cg.fds.exception.ItemNotFoundException;
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
		FoodCart cart= Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService.increaseQuantity(cart, item, 1));
		Mockito.verify(cartService).increaseQuantity(cart, item, 1);
		Mockito.verify(cartService).validateCart(cart);
	}


	/**
	 * scenario  , cart is null
	 */
	
	@Test
	void addItemToCartTest_2() {
		FoodCart cart= new FoodCart();
		Mockito.doThrow(CartNotExistException.class).when(cartService).validateCart(cart);
		Executable executable = () -> cartService.cartExist(cart);
		Assertions.assertThrows(CartNotExistException.class, executable);

	}
	
	@Test
	void increaseQuantityTest_1() {
		int quantity=1;
		String cartItemId="string";
		FoodCart cart=Mockito.mock(FoodCart.class);
		CartItem cartItem=Mockito.mock(CartItem.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		Mockito.when(CartItem.id(cart, item)).thenReturn(cartItemId);
		Optional<CartItem>optionCartItem=Optional.of(cartItem);
		Mockito.when(cartItemRepository.findById(cartItemId)).thenReturn(optionCartItem);
		Mockito.when(optionCartItem.isPresent()).thenReturn(true);
		CartItem cartItemSaved=Mockito.mock(CartItem.class);
		Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItemSaved);
		FoodCart result=cartService.increaseQuantity(cart, item, quantity);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);	
	}
	@Test
	void increaseQuantityTest_2() {
		int existingQuantity=1;
		int quantity=1;
		int updatedQuantity=2;
		String cartItemId="string";
		FoodCart cart=Mockito.mock(FoodCart.class);
		CartItem cartItem=Mockito.mock(CartItem.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		Mockito.when(CartItem.id(cart, item)).thenReturn(cartItemId);
		Optional<CartItem>optionCartItem=Optional.of(cartItem);
		Mockito.when(cartItemRepository.findById(cartItemId)).thenReturn(optionCartItem);
		Mockito.when(optionCartItem.isPresent()).thenReturn(false);

		Mockito.when(optionCartItem.get()).thenReturn(cartItem);
		Mockito.doReturn(cartItem).when(optionCartItem).get();
		Mockito.doReturn(existingQuantity).when(cartItem).getQuantity();
		
		CartItem cartItemSaved=Mockito.mock(CartItem.class);
		Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItemSaved);
		FoodCart result=cartService.increaseQuantity(cart, item, quantity);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);	
		Mockito.verify(cartItem).setQuantity(updatedQuantity);
		
	}
	
//	
//	@Test
//	void reduceQuantityTest_1() {
//		
//		int existingQuantity=2;
//		int quantity=1;
//		int updatedQuantity=1;
//		String cartItemId="string";
//		FoodCart cart=Mockito.mock(FoodCart.class);
//		CartItem cartItem=Mockito.mock(CartItem.class);
//		Mockito.doNothing().when(cartService).validateCart(cart);
//		Mockito.doNothing().when(cartService).cartExist(cart);
//		FoodCart cartSaved=Mockito.mock(FoodCart.class);
//		Item item=Mockito.mock(Item.class);
//		Mockito.when(CartItem.id(cart, item)).thenReturn(cartItemId);
//		Optional<CartItem>optionCartItem=Optional.of(cartItem);
//		Mockito.when(cartItemRepository.findById(cartItemId)).thenReturn(optionCartItem);
//		Mockito.when(optionCartItem.isPresent()).thenReturn(true);
//		Mockito.when(optionCartItem.get()).thenReturn(cartItem);
//		Mockito.doReturn(cartItem).when(optionCartItem).get();
//		Mockito.doReturn(existingQuantity).when(cartItem).getQuantity();
//		CartItem cartItemSaved=Mockito.mock(CartItem.class);
//		Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItemSaved);
//		FoodCart result=cartService.increaseQuantity(cart, item, quantity);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(cartSaved, result);
//		Mockito.verify(cartService).validateCart(cart);
//		Mockito.verify(cartService).cartExist(cart);	
//	}
//	@Test
//	void reduceQuantityTest_2() {
//		int quantity=1;
//		FoodCart cart=Mockito.mock(FoodCart.class);
//		Mockito.doNothing().when(cartService).validateCart(cart);
//		Mockito.doNothing().when(cartService).cartExist(cart);
//		Item item=Mockito.mock(Item.class);
//		List<Item>items=Mockito.mock(List.class);
//		Mockito.when(cart.getItemList()).thenReturn(items);
//		Mockito.when(items.contains(item)).thenReturn(false);
//		Executable executable = () -> cartService.increaseQuantity(cart, item, quantity);
//		Assertions.assertThrows(ItemNotFoundException.class, executable);		
//	}
	
	
	@Test
	void removeTest_1() {
		String cartItemId="string";
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
//		CartItem cartItem=Mockito.mock(CartItem.class);
		Item item=Mockito.mock(Item.class);
		Mockito.when(CartItem.id(cart, item)).thenReturn(cartItemId);
		FoodCart result=cartService.removeItem(cart, item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);
		Mockito.verify(cartItemRepository).deleteById(cartItemId);
	}
	
	@Test
	void clearCartTest_1() {
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart result=cartService.clearCart(cart);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);	
	}
	
	@Test
	public void validateCartTest() {
		FoodCart cart=null;
		Executable executable = () -> cartService.validateCart(cart);
		Assertions.assertThrows(InvalidCartException.class, executable);
	}
	
	@Test
	public void cartExistTest() {
		String cartId="1";
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.when(cart.getCartId()).thenReturn(cartId);
		Mockito.when(cartRepository.existsById(cartId)).thenReturn(false);
		Executable executable = () -> cartService.cartExist(cart);
		Assertions.assertThrows(CartNotExistException.class, executable);
	}
	
	

}
