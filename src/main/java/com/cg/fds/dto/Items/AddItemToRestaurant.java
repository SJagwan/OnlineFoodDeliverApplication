package com.cg.fds.dto.Items;

import javax.validation.constraints.NotBlank;

public class AddItemToRestaurant {
	@NotBlank
	private String itemId;
	@NotBlank
	private String restaurantId;
	
	public AddItemToRestaurant()
	{
		
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
