package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.exception.CartNotExistException;
import com.cg.fds.exception.InvalidCartException;
import com.cg.fds.exception.InvalidRestaurantLocationException;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.IItemRepository;

@ExtendWith(MockitoExtension.class)
public class CartServiceImpUnitTest {
	
	@Mock
	ICartRepository cartRepository;
	
	@Mock
	IItemRepository itemRepository;
	
	@Spy
	@InjectMocks
	CartServiceImp cartService;
	
	
	@Test
	void addItemToCartTest() {
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		List<Item>items=new ArrayList<>();
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(cartRepository.save(cart)).thenReturn(cartSaved);
		FoodCart result=cartService.addItemToCart(cart, item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
		Mockito.verify(cart).setItemList(items);
		Mockito.verify(cart).getItemList();
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);
	}
	
	@Test
	void increaseQuantityTest_1() {
		int quantity=1;
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		List<Item>items=Mockito.mock(List.class);
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(items.contains(item)).thenReturn(true);
		item.setQuantity(quantity);
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		Mockito.when(cartRepository.save(cart)).thenReturn(cartSaved);
		FoodCart result=cartService.increaseQuantity(cart, item, quantity);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);	
	}
	@Test
	void increaseQuantityTest_2() {
		int quantity=1;
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		Item item=Mockito.mock(Item.class);
		List<Item>items=Mockito.mock(List.class);
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(items.contains(item)).thenReturn(false);
		Executable executable = () -> cartService.increaseQuantity(cart, item, quantity);
		Assertions.assertThrows(ItemNotFoundException.class, executable);		
	}
	
	
	@Test
	void reduceQuantityTest_1() {
		int quantity=1;
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		List<Item>items=Mockito.mock(List.class);
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(items.contains(item)).thenReturn(true);
		item.setQuantity(quantity);
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		Mockito.when(cartRepository.save(cart)).thenReturn(cartSaved);
		FoodCart result=cartService.increaseQuantity(cart, item, quantity);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);	
	}
	@Test
	void reduceQuantityTest_2() {
		int quantity=1;
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		Item item=Mockito.mock(Item.class);
		List<Item>items=Mockito.mock(List.class);
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(items.contains(item)).thenReturn(false);
		Executable executable = () -> cartService.increaseQuantity(cart, item, quantity);
		Assertions.assertThrows(ItemNotFoundException.class, executable);		
	}
	
	
	@Test
	void removeTest_1() {
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		Item item=Mockito.mock(Item.class);
		List<Item>items=Mockito.mock(List.class);
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(items.contains(item)).thenReturn(true);
		items.remove(item);
		Mockito.when(cartRepository.save(cart)).thenReturn(cartSaved);
		FoodCart result=cartService.removeItem(cart, item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
		Mockito.verify(cartService).validateCart(cart);
		Mockito.verify(cartService).cartExist(cart);	
	}
	@Test
	void removeTest_2() {
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		Item item=Mockito.mock(Item.class);
		List<Item>items=Mockito.mock(List.class);
		Mockito.when(cart.getItemList()).thenReturn(items);
		Mockito.when(items.contains(item)).thenReturn(false);
		Executable executable = () -> cartService.removeItem(cart, item);
		Assertions.assertThrows(ItemNotFoundException.class, executable);		
	}
	
	@Test
	void clearCartTest_1() {
		FoodCart cart=Mockito.mock(FoodCart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doNothing().when(cartService).cartExist(cart);
		FoodCart cartSaved=Mockito.mock(FoodCart.class);
		cart.setItemList(null);
		Mockito.when(cartRepository.save(cart)).thenReturn(cartSaved);
		FoodCart result=cartService.clearCart(cart);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cartSaved, result);
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
		Mockito.when(cartRepository.existsById(cartId)).thenReturn(false);
		Executable executable = () -> cartService.cartExist(cart);
		Assertions.assertThrows(CartNotExistException.class, executable);
	}
	
	

}
