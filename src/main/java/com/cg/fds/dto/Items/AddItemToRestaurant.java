package com.cg.fds.dto.Items;

public class AddItemToRestaurant {
	private String itemId;
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
