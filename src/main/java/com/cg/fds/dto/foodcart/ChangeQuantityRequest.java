package com.cg.fds.dto.foodcart;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChangeQuantityRequest {
	@NotBlank
	private String customerId;
	@NotBlank
	private String itemId;
	@Min(1)
	private int quantity;

	public ChangeQuantityRequest() {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
