package com.cg.fds.dto.Bill;

import com.cg.fds.entities.OrderDetails;

public class BillUpdateRequest {
	private int billId;
	
	private int totalItem;
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
