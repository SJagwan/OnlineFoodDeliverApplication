package com.cg.fds.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.restaurant.AddRestaurantRequest;
import com.cg.fds.dto.restaurant.RestaurantDetails;
import com.cg.fds.dto.restaurant.UpdateRestaurantRequest;
import com.cg.fds.dto.restaurant.ViewAllByCustomerIdRequest;
import com.cg.fds.dto.restaurant.ViewOrDeleteOrderRequest;
import com.cg.fds.entities.Address;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.CustomerServiceImp;

import com.cg.fds.service.IRestaurantService;
import com.cg.fds.util.AddressUtil;
import com.cg.fds.util.RestaurantUtil;



@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
	
	@Autowired
    private IRestaurantService restaurantService;
	
	
	@Autowired
	private AddressUtil addressUtil ;
	
	@Autowired
	private RestaurantUtil restaurantUtil;
	
	@PostMapping("/addrestaurant")
	public RestaurantDetails addRestaurant(@RequestBody AddRestaurantRequest request) {
		Restaurant res=new Restaurant();
		res.setRestaurantId(restaurantUtil.generateId());
		res.setContactNumber(request.getContactNumber());
		res.setManagerName(request.getManagerName());
		res.setRestaurantName(request.getRestaurantName());
		Address address = addressUtil.getAddress();
		address.setAddressId(addressUtil.generateId());
		address.setArea(request.getArea());
		address.setBuildingName(request.getBuildingName());
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setPincode(request.getPincode());
		address.setState(request.getState());
		address.setStreetNo(request.getStreetNo());
		res.setAddress(address);
		return restaurantUtil.toRestaurantDetails(restaurantService.addRestaurant(res));
	}
	
	@PutMapping("/updaterestaurant")
	public RestaurantDetails updateRestaurant(@RequestBody UpdateRestaurantRequest request) {
		Restaurant res=restaurantService.viewRestaurant(request.getRestaurantId());
		res.setContactNumber(request.getContactNumber());
		res.setManagerName(request.getManagerName());
		res.setRestaurantName(request.getRestaurantName());
		return restaurantUtil.toRestaurantDetails(restaurantService.updateRestaurant(res));
	}
	
	@DeleteMapping("/deleterestaurant/{id}")
	public RestaurantDetails deleteRestaurant(@PathVariable String id) {
		Restaurant res=restaurantService.viewRestaurant(id);
		return restaurantUtil.toRestaurantDetails(restaurantService.removeRestaurant(res));
	}

	@GetMapping("/viewrestaurant/{id}")
	public RestaurantDetails viewRestaurant(@PathVariable String id) {
		
		return restaurantUtil.toRestaurantDetails(restaurantService.viewRestaurant(id));
	}
	
	@GetMapping("/viewbynamerestaurant/{name}")
	public List<RestaurantDetails> viewByNameRestaurant(@PathVariable String name) {
		
		return restaurantUtil.toRestaurantDetailsList(restaurantService.viewRestaurantByItemName(name));
	}
	
	@GetMapping("/viewallrestaurant")
	public List<RestaurantDetails> viewAllRestaurant() {
		
		return restaurantUtil.toRestaurantDetailsList(restaurantService.viewAllRestaurants());
	}
	
	@GetMapping("/viewbylocationrestaurant/{location}")
	public List<RestaurantDetails> viewByLocationRestaurant(@PathVariable String location) {
		
		return restaurantUtil.toRestaurantDetailsList(restaurantService.viewNearByRestaurant(location));
	}
	

}
