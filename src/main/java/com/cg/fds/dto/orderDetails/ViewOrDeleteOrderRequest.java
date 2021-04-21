package com.cg.fds.dto.orderDetails;

import javax.validation.constraints.NotNull;

public class ViewOrDeleteOrderRequest {

	@NotNull(message="Order id cannot be null")
	private int orderId;

	public ViewOrDeleteOrderRequest() {

	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
