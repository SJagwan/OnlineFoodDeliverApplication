package com.cg.fds.dto.orderDetails;

import javax.validation.constraints.NotBlank;

public class AddOrderRequest {
	@NotBlank
	private String customerId;

	public AddOrderRequest() {
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
