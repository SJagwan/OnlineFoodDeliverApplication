package com.cg.fds.dto.foodcart;

import javax.validation.constraints.NotBlank;

public class FindCartRequest {

	@NotBlank(message="CustomerId cannot be null in foodcart to find FoodCart")
	private String customerId;

	public FindCartRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
