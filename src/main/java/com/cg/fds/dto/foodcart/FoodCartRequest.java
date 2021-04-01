package com.cg.fds.dto.foodcart;

import javax.validation.constraints.NotBlank;

public class FoodCartRequest {
	@NotBlank
	private String customerId;
	@NotBlank
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
