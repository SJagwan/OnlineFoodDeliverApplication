package com.cg.fds.dto.foodcart;

import javax.validation.constraints.NotBlank;

public class FindCartRequest {

	@NotBlank
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
