package com.cg.fds.dto.Items;

import javax.validation.constraints.NotBlank;

public class RemoveItem {
	@NotBlank(message="Item Id cannot be null")
	private String itemId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
