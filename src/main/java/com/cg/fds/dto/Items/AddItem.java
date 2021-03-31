package com.cg.fds.dto.Items;



import java.time.LocalDateTime;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.OrderDetails;

public class AddItem {

	private String itemName;
	private String catId;
	private int quantity;
	private double cost;
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
