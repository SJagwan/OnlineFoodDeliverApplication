package com.cg.fds.dto.orderDetails;

import javax.validation.constraints.NotNull;

public class UpdateOrderRequest {

	private String orderStatus;
	
	@NotNull(message="Order id cannot be null")
	private int orderId;

	public UpdateOrderRequest() {
	}

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
