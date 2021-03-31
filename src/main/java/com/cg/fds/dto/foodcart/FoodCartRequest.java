package com.cg.fds.dto.foodcart;

public class FoodCartRequest {
	private String customerId;
	private String itemId;
	public FoodCartRequest() {
		
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
	
	

}
