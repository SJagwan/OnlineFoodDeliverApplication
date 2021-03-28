package com.cg.fds.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidRestaurantException;
import com.cg.fds.exception.InvalidRestaurantLocationException;
import com.cg.fds.exception.InvalidRestaurantNameException;
import com.cg.fds.exception.RemoveRestaurantException;
import com.cg.fds.exception.RestaurantNotFoundException;
import com.cg.fds.exception.UpdateRestaurantException;
import com.cg.fds.repository.IAddressRepository;
import com.cg.fds.repository.IRestaurantRepository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImpUnitTest {

	@Mock
	IRestaurantRepository restaurantRepository;
	
	@Mock
	IAddressRepository addressRepository;

	@Spy
	@InjectMocks
	RestaurantServiceImp restaurantService;

	/**
	 * Testing addRestaurant Scenario if Restaurant is restaurantSaved expected =
	 * restaurantSaved result = restaurantSaved
	 */

	@Test
	public void addRestaurantTest() {

		Restaurant restaurant = Mockito.mock(Restaurant.class);
		Address address=Mockito.mock(Address.class);
		Mockito.doNothing().when(restaurantService).validateRestaurant(restaurant);
		Restaurant restaurantSaved = Mockito.mock(Restaurant.class);
		Mockito.when(restaurant.getAddress()).thenReturn(address);
		Mockito.when(addressRepository.save(address)).thenReturn(address);
		Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurantSaved);
		Restaurant result = restaurantService.addRestaurant(restaurant);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(restaurantSaved, result);
		Mockito.verify(restaurantRepository).save(restaurant);

	}

	@Test
	public void removeRestaurantTest_1() {
		String restaurantId = "1";
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		Mockito.doNothing().when(restaurantService).validateRestaurant(restaurant);
		Mockito.when(restaurant.getRestaurantId()).thenReturn(restaurantId);
		Mockito.when(restaurantRepository.existsById(restaurantId)).thenReturn(true);
		Restaurant result = restaurantService.removeRestaurant(restaurant);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(restaurant, result);
	}

	@Test
	public void removeRestaurantTest_2() {
		String restaurantId = "1";
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		Mockito.doNothing().when(restaurantService).validateRestaurant(restaurant);
		Mockito.when(restaurant.getRestaurantId()).thenReturn(restaurantId);
		Mockito.when(restaurantRepository.existsById(restaurantId)).thenReturn(false);
		Executable executable = () -> restaurantService.removeRestaurant(restaurant);
		Assertions.assertThrows(RemoveRestaurantException.class, executable);
	}

	@Test
	public void viewRestaurantTest_1() {
		String id = "1";
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		Optional<Restaurant> optionalSaved = Optional.of(restaurant);
		Mockito.when(restaurantRepository.findById(id)).thenReturn(optionalSaved);
		Restaurant result = restaurantService.viewRestaurant(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(restaurant, result);
	}

	@Test
	public void viewRestaurantTest_2() {
		String id = "1";
		Optional<Restaurant> optionalSaved = Optional.empty();
		Mockito.when(restaurantRepository.findById(id)).thenReturn(optionalSaved);
		Executable executable = () -> restaurantService.viewRestaurant(id);
		Assertions.assertThrows(RestaurantNotFoundException.class, executable);
	}

	/**
	 * Testing updateRestaurant Scenario if Restaurant is restaurantSaved expected =
	 * restaurantSaved result = restaurantSaved
	 */
	@Test
	public void updateRestaurantTest_1() {
		String restaurantId = "1";
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		Mockito.doNothing().when(restaurantService).validateRestaurant(restaurant);
		Restaurant restaurantSaved = Mockito.mock(Restaurant.class);
		Mockito.when(restaurant.getRestaurantId()).thenReturn(restaurantId);
		Mockito.when(restaurantRepository.existsById(restaurantId)).thenReturn(true);
		Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurantSaved);
		Restaurant result = restaurantService.updateRestaurant(restaurant);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(restaurantSaved, result);
	}

	@Test
	public void updateRestaurantTest_2() {
		String restaurantId = "1";
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		Mockito.doNothing().when(restaurantService).validateRestaurant(restaurant);
		Mockito.when(restaurant.getRestaurantId()).thenReturn(restaurantId);
		Mockito.when(restaurantRepository.existsById(restaurantId)).thenReturn(false);
		Executable executable = () -> restaurantService.updateRestaurant(restaurant);
		Assertions.assertThrows(UpdateRestaurantException.class, executable);
	}

	/**
	 * Testing viewNearByRestaurant Scenario if location is "Delhi" & List of
	 * Restaurants is in lists expected = (list<restaurant>) result result=
	 * (list<restaurant>) result
	 */

//	@Test
//	public void viewNearByRestaurantTest() {
//		String location = "Delhi";
//		List<Restaurant> list = Mockito.mock(List.class);
//		Mockito.when(restaurantRepository.findByLocation(location)).thenReturn(list);
//		List<Restaurant> result = restaurantService.viewNearByRestaurant(location);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(list, result);
//	}
//
//	/**
//	 * Testing viewRestaurantByItemName Scenario if Item name is "Noodles" & List of
//	 * Restaurants is in lists expected = (list<restaurant>) result result =
//	 * (list<restaurant>) result
//	 */
//	@Test
//	public void viewRestaurantByItemNameTest() {
//		String name = "Noodles";
//		List<Restaurant> list = Mockito.mock(List.class);
//		Mockito.when(restaurantRepository.findRestaurantByItemName(name)).thenReturn(list);
//		List<Restaurant> result = restaurantService.viewRestaurantByItemName(name);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(list, result);
//	}

	/**
	 * Scenario if Restaurant Name in null;
	 */
	@Test
	public void validateRestaurantNameTest() {
		String name = " ";
		Executable executable = () -> restaurantService.validateRestaurantName(name);
		Assertions.assertThrows(InvalidRestaurantNameException.class, executable);
	}

	/**
	 * Scenario if Restaurant in null;
	 */
	@Test
	public void validateRestaurant() {
		Restaurant restaurant = null;
		Executable executable = () -> restaurantService.validateRestaurant(restaurant);
		Assertions.assertThrows(InvalidRestaurantException.class, executable);
	}

	/**
	 * Scenario if Restaurant Location in null;
	 */
	@Test
	public void validateRestaurantLocationTest() {
		String location = " ";
		Executable executable = () -> restaurantService.validateRestaurantLocation(location);
		Assertions.assertThrows(InvalidRestaurantLocationException.class, executable);
	}

}
