package com.cg.fds.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.service.IRestaurantService;
import com.cg.fds.util.AddressUtil;
import com.cg.fds.util.RestaurantUtil;

@Validated
@RequestMapping("/restaurants")
@RestController
public class RestaurantController {

	@Autowired
	private IRestaurantService restaurantService;

	@Autowired
	private AddressUtil addressUtil;

	@Autowired
	private RestaurantUtil restaurantUtil;

	@PostMapping("/add")
	public RestaurantDetails addRestaurant(@RequestBody @Valid AddRestaurantRequest request) {
		Restaurant res = new Restaurant();
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

	@PutMapping("/update")
	public RestaurantDetails updateRestaurant(@RequestBody @Valid UpdateRestaurantRequest request) {
		Restaurant res = restaurantService.viewRestaurant(request.getRestaurantId());
		res.setContactNumber(request.getContactNumber());
		res.setManagerName(request.getManagerName());
		res.setRestaurantName(request.getRestaurantName());
		return restaurantUtil.toRestaurantDetails(restaurantService.updateRestaurant(res));
	}

	@DeleteMapping("/delete/{id}")
	public RestaurantDetails deleteRestaurant(@PathVariable @NotBlank(message="Restaurant Id cannot be null") String id) {
		Restaurant res = restaurantService.viewRestaurant(id);
		return restaurantUtil.toRestaurantDetails(restaurantService.removeRestaurant(res));
	}

	@GetMapping("/view/{id}")
	public RestaurantDetails viewRestaurant(@PathVariable @NotBlank(message="Restaurant Id cannot be null") String id) {

		return restaurantUtil.toRestaurantDetails(restaurantService.viewRestaurant(id));
	}

	@GetMapping("/viewbyname/{name}")
	public List<RestaurantDetails> viewByNameRestaurant(@PathVariable @NotBlank(message="Restaurant name cannot be null") String name) {

		return restaurantUtil.toRestaurantDetailsList(restaurantService.viewRestaurantByItemName(name));
	}

	@GetMapping("/viewall")
	public List<RestaurantDetails> viewAllRestaurant() {

		return restaurantUtil.toRestaurantDetailsList(restaurantService.viewAllRestaurants());
	}

	@GetMapping("/viewbylocation/{location}")
	public List<RestaurantDetails> viewByLocationRestaurant(@PathVariable @NotBlank(message="Location cannot be null") String location) {

		return restaurantUtil.toRestaurantDetailsList(restaurantService.viewNearByRestaurant(location));
	}

}
