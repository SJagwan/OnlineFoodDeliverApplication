package com.cg.fds.dto.foodcart;

import java.util.List;

import com.cg.fds.entities.Item;

public class FoodCartDetails {
	private String customerId;
	private String firstName;
	private List<FoodCartItemDetails> items;
	public FoodCartDetails() {
		
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<FoodCartItemDetails> getItems() {
		return items;
	}
	public void setItems(List<FoodCartItemDetails> items) {
		this.items = items;
	}

	


}
