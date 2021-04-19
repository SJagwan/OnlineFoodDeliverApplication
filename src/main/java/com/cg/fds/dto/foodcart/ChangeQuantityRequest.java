package com.cg.fds.dto.foodcart;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChangeQuantityRequest {
	@NotBlank(message="customerId cannot be null in foodcart for changing quantity")
	private String customerId;
	@NotBlank(message="ItemId cannot be null in foodcart for changing quantity")
	private String itemId;
	@Min(value=1)
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
