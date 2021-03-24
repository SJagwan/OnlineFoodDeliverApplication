package com.cg.fds.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.InvalidRestaurantException;
import com.cg.fds.exception.InvalidRestaurantLocationException;
import com.cg.fds.exception.InvalidRestaurantNameException;
import com.cg.fds.repository.IRestaurantRepository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImpTest {
	
	@Mock
	IRestaurantRepository restaurantRepository;
	
	@Spy
	@InjectMocks
	RestaurantServiceImp restaurantService;
	
	


	@Test
	public void updateRestaurantTest() {
		Restaurant restaurant=Mockito.mock(Restaurant.class);
		Restaurant restaurantSaved=Mockito.mock(Restaurant.class);
		Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurantSaved);
		Restaurant result=restaurantService.updateRestaurant(restaurant);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(restaurantSaved,result);		
	}
	@Test
	public void viewNearByRestaurantTest() {
		String location="Delhi";
		List<Restaurant> list=Mockito.mock(List.class);
		Mockito.when(restaurantRepository.findByLocation(location)).thenReturn(list);
		List<Restaurant> result=restaurantService.viewNearByRestaurant(location);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(list,result);		
	}
	@Test
	public void viewRestaurantByItemNameTest() {
		String name="Pranav";
		List<Restaurant> list=Mockito.mock(List.class);
		Mockito.when(restaurantRepository.findRestaurantByItemName(name)).thenReturn(list);
		List<Restaurant> result=restaurantService.viewRestaurantByItemName(name);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(list,result);		
	}
	@Test
    public void validateRestaurantNameTest(){
        String name=" ";
        Executable executable=()-> restaurantService.validateRestaurantName(name);
        Assertions.assertThrows(InvalidRestaurantNameException.class,executable);
    }
	@Test
    public void validateRestaurant(){
        Restaurant restaurant=null;
        Executable executable=()-> restaurantService.validateRestaurant(restaurant);
        Assertions.assertThrows(InvalidRestaurantException.class,executable);
    }
	@Test
    public void validateRestaurantLocationTest(){
        String location=" ";
        Executable executable=()-> restaurantService.validateRestaurantLocation(location);
        Assertions.assertThrows(InvalidRestaurantLocationException.class,executable);
    }
	
}
