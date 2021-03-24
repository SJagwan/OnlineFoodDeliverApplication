package com.cg.fds.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.fds.repository.IRestaurantRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImpTest {
	
	@Mock
	IRestaurantRepository orderRepository;
	
	@Spy
	@InjectMocks
	RestaurantServiceImp orderService;
	
	@Test
	void addRestaurantTest() {
		
	}

}
