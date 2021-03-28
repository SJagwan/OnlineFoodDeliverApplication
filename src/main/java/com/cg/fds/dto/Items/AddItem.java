package com.cg.fds.dto.Items;



import java.time.LocalDateTime;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.OrderDetails;

public class AddItem {

	private String itemName;
	private Category category;
	private int quantity;
	private double cost;

	
	public AddItem() {
		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
