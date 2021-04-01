package com.cg.fds.dto.orderDetails;

import javax.validation.constraints.NotNull;

public class ViewOrDeleteOrderRequest {

	@NotNull
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
