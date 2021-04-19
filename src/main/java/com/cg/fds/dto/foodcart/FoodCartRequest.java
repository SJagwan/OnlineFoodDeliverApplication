package com.cg.fds.dto.foodcart;

import javax.validation.constraints.NotBlank;

public class FoodCartRequest {
	@NotBlank(message="CustomerId cannot be null in foodcart")
	private String customerId;
	@NotBlank(message="ItemId cannot be null in foodcart")
	private String itemId;

	public FoodCartRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
