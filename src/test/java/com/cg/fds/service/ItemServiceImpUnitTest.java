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

import com.cg.fds.entities.Item;

import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.exception.RemoveItemException;
import com.cg.fds.exception.UpdateItemException;

import com.cg.fds.repository.IItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImpUnitTest {
	
	@Mock
	IItemRepository itemRepository;
	
	@Spy
	@InjectMocks
	ItemServiceImp itemService;
	
	
	@Test
	public void addItemTest() {
		Item item = Mockito.mock(Item.class);
		Mockito.doNothing().when(itemService).validateItem(item);
		Item itemSaved = Mockito.mock(Item.class);
		Mockito.when(itemRepository.save(item)).thenReturn(itemSaved);
		Item result = itemService.addItem(item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(itemSaved, result);
		Mockito.verify(itemRepository).save(item);

	}
	
	@Test
	public void viewItemTest_1() {
		String id = "1";
		Item item = Mockito.mock(Item.class);
		Optional<Item> optionalSaved = Optional.of(item);
		Mockito.when(itemRepository.findById(id)).thenReturn(optionalSaved);
		Item result = itemService.viewItem(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(item, result);
	}

	@Test
	public void viewItemTest_2() {
		String id = "1";
		Optional<Item> optionalSaved = Optional.empty();
		Mockito.when(itemRepository.findById(id)).thenReturn(optionalSaved);
		Executable executable = () -> itemService.viewItem(id);
		Assertions.assertThrows(ItemNotFoundException.class, executable);
	}
	
	@Test
	public void updateItemTest_1() {
		String itemId = "1";
		Item item = Mockito.mock(Item.class);
		Mockito.doNothing().when(itemService).validateItem(item);
		Item itemUpdated= Mockito.mock(Item.class);
		Mockito.when(itemRepository.existsById(itemId)).thenReturn(true);
		Mockito.when(itemRepository.save(item)).thenReturn(itemUpdated);
		Item result = itemService.updateItem(item);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(itemUpdated, result);
	}

	@Test
	public void updateItemTest_2() {
		String itemId = "1";
		Item item = Mockito.mock(Item.class);
		Mockito.doNothing().when(itemService).validateItem(item);
		Mockito.when(itemRepository.existsById(itemId)).thenReturn(false);
		Executable executable = () -> itemService.updateItem(item);
		Assertions.assertThrows(UpdateItemException.class, executable);
	}
	
	@Test
	public void removeItemTest_1() {
		String itemId = "1";
		Item item = Mockito.mock(Item.class);
		Item itemRemoved= Mockito.mock(Item.class);
		Mockito.when(itemRepository.existsById(itemId)).thenReturn(true);
		Mockito.when(itemRepository.remove(itemId)).thenReturn(itemRemoved);
		Item result = itemService.removeItem(itemId);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(itemRemoved, result);
	}

	@Test
	public void removeItemTest_2() {
		String itemId = "1";
		Mockito.when(itemRepository.existsById(itemId)).thenReturn(false);
		Executable executable = () -> itemService.removeItem(itemId);
		Assertions.assertThrows(RemoveItemException.class, executable);
	}

}
