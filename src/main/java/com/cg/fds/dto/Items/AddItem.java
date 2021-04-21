package com.cg.fds.dto.Items;

import javax.validation.constraints.NotBlank;

public class AddItem {

	private String itemName;
	
	@NotBlank(message="CategoryId cannot be null in item")
	private String catId;
	private int quantity;
	private double cost;
	
	@NotBlank(message="RestaurantId cannot be null in item")
	private String restaurantId;

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public AddItem() {

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
