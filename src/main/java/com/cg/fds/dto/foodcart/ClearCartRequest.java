package com.cg.fds.dto.foodcart;

import javax.validation.constraints.NotBlank;

public class ClearCartRequest {

	@NotBlank(message="CustomerId cannot be null in foodcart for clearing Cart")
	private String customerId;

	public ClearCartRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
