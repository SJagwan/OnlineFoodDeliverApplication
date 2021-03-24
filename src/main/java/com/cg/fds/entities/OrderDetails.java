package com.cg.fds.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetails {

	@Id
	private Integer orderId;
	private LocalDateTime orderDate;
	private FoodCart cart;
	private String orderStatus;
	
	
	public OrderDetails() {
		//Do Nothing
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public FoodCart getCart() {
		return cart;
	}
	public void setCart(FoodCart cart) {
		this.cart = cart;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	

}
