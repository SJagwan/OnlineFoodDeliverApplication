package com.cg.fds.dto.orderDetails;

import javax.validation.constraints.NotBlank;

public class AddOrderRequest {
	@NotBlank(message="CustomerId cannot be null for OrderDetails")
	private String customerId;

	public AddOrderRequest() {
		// Do Nothing
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
