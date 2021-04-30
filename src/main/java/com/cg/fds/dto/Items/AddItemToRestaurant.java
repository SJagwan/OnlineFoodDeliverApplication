package com.cg.fds.dto.Items;

import javax.validation.constraints.NotBlank;

public class AddItemToRestaurant {
	@NotBlank(message="Item id cannot be null")
	private String itemId;
	@NotBlank(message="restaurantId cannot be null in item")
	private String restaurantId;
	
	public AddItemToRestaurant()
	{
		// Do Nothing
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
}
