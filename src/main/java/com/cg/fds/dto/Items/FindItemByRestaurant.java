package com.cg.fds.dto.Items;

import javax.validation.constraints.NotBlank;

public class FindItemByRestaurant {
	@NotBlank(message="RestaurantId cannot be null in item")
	private String restaurantId;

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
}
