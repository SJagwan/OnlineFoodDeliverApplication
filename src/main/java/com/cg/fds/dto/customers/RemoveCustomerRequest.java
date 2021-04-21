package com.cg.fds.dto.customers;

import javax.validation.constraints.NotBlank;

public class RemoveCustomerRequest {

	@NotBlank(message="Id cannot be null for customer")
	private String id;

	public RemoveCustomerRequest() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
