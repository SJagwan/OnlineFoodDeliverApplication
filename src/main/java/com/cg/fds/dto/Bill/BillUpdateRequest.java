package com.cg.fds.dto.Bill;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BillUpdateRequest {
	@NotNull(message="Bill id cannot be null")
	private int billId;

	@NotNull
	@Min(value=1,message="Total item quantity cannot be less than zero")
	private int totalItem;
	@NotNull
	private double totalCost;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

}
