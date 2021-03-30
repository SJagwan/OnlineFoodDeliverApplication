package com.cg.fds.dto.orderDetails;

public class UpdateOrderRequest {
	
	private String orderStatus;
	private int orderId;
	
	public UpdateOrderRequest() {}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
	

}
