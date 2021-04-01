package com.cg.fds.dto.foodcart;

import javax.validation.constraints.NotBlank;

public class ClearCartRequest {

	@NotBlank
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
