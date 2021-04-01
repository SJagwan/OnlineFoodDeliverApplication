package com.cg.fds.dto.orderDetails;

import javax.validation.constraints.NotBlank;

public class ViewAllByCustomerIdRequest {

	@NotBlank
	private String customerId;

	public ViewAllByCustomerIdRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
