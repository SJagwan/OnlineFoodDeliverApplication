package com.cg.fds.dto.Bill;

import javax.validation.constraints.NotNull;

public class BillRequest {
	@NotNull(message="Bill id cannot be null")
	private int billId;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

}
