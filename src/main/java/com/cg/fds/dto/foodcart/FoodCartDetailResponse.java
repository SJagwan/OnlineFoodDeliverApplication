package com.cg.fds.dto.foodcart;

public class FoodCartDetailResponse {

	private String itemId;
	private String itemName;
	private int quantity;
	private double cost;
	

	public FoodCartDetailResponse(){

    }

	public FoodCartDetailResponse(String itemId,String itemName, int quantity,double cost){
        this.itemId=itemId;
        this.quantity=quantity;
        this.cost=cost;
        this.itemName=itemName;
        
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	

}
