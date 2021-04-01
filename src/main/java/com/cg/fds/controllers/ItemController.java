package com.cg.fds.controllers;

import java.util.ArrayList;
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

import com.cg.fds.dto.Items.AddItem;
import com.cg.fds.dto.Items.AddItemToRestaurant;
import com.cg.fds.dto.Items.FindItemByCategory;
import com.cg.fds.dto.Items.FindItemByRestaurant;
import com.cg.fds.dto.Items.ItemDetails;
import com.cg.fds.dto.Items.RemoveItem;
import com.cg.fds.dto.Items.UpdateItem;
import com.cg.fds.dto.restaurant.RestaurantDetails;
import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.service.CategoryServiceImp;
import com.cg.fds.service.IItemService;
import com.cg.fds.service.RestaurantServiceImp;
import com.cg.fds.util.ItemUtil;
import com.cg.fds.util.RestaurantUtil;

@Validated
@RequestMapping("/items")
@RestController
public class ItemController {

	@Autowired
	private IItemService itemService;

	@Autowired
	private ItemUtil itemUtil;

	@Autowired
	private CategoryServiceImp categoryService;

	@Autowired
	private RestaurantServiceImp restaurantService;

	@Autowired
	private RestaurantUtil restaurantUtil;

	@PostMapping("/add")
	public ItemDetails addItem(@RequestBody @Valid AddItem request) {
		Item item = itemUtil.getItem();
		Restaurant restaurant = restaurantService.viewRestaurant(request.getRestaurantId());
		Category category = categoryService.viewCategory(request.getCatId());
		item.setCategory(category);
		item.setCost(request.getCost());
		item.setItemId(itemUtil.generateId());
		item.setItemName(request.getItemName());
		item.setQuantity(request.getQuantity());
		List<Restaurant> restaurants = item.getRestaurants();
		if (restaurants == null) {
			restaurants = new ArrayList<>();
			item.setRestaurants(restaurants);
		}
		restaurants.add(restaurant);
		item = itemService.addItem(item);
		return itemUtil.toItemDetails(item);
	}

	@PutMapping("/addtorestaurant")
	public RestaurantDetails addItemToRestaurant(@RequestBody @Valid AddItemToRestaurant request) {
		Item item = itemService.viewItem(request.getItemId());
		Restaurant restaurant = restaurantService.viewRestaurant(request.getRestaurantId());
		List<Item> restaurantItems = restaurant.getItemList();
		if (!restaurantItems.contains(item)) {
			restaurantItems.add(item);
		}
		restaurant = restaurantService.updateRestaurant(restaurant);
		return restaurantUtil.toRestaurantDetails(restaurant);
	}

	@PutMapping("/update")
	public ItemDetails updateItem(@RequestBody @Valid UpdateItem request) {
		Item item = itemService.viewItem(request.getItemId());
		item.setCost(request.getCost());
		item.setItemName(request.getItemName());
		item.setQuantity(request.getQuantity());
		return itemUtil.toItemDetails(itemService.updateItem(item));

	}

	@DeleteMapping("/remove")
	public ItemDetails removeItem(@RequestBody @Valid RemoveItem request) {
		return itemUtil.toItemDetails(itemService.removeItem(request.getItemId()));
	}

	@GetMapping("/view/{id}")
	public ItemDetails viewItem(@PathVariable @NotBlank String id) {
		return itemUtil.toItemDetails(itemService.viewItem(id));
	}

	@GetMapping("/byname/{name}")
	public List<ItemDetails> viewItembyname(@PathVariable @NotBlank String name) {
		return itemUtil.toItemDetailsList(itemService.viewAllItemsByName(name));
	}

	@GetMapping("/bycategory")
	public List<ItemDetails> viewItembycategory(@RequestBody @Valid FindItemByCategory request) {
		Category category = categoryService.viewCategory(request.getCatId());
		return itemUtil.toItemDetailsList(itemService.viewAllItems(category));
	}

	@GetMapping("/byrestaurant")
	public List<ItemDetails> viewItembyrestaurant(@RequestBody @Valid FindItemByRestaurant request) {
		Restaurant restaurant = restaurantService.viewRestaurant(request.getRestaurantId());
		return itemUtil.toItemDetailsList(itemService.viewAllItems(restaurant));
	}

}
