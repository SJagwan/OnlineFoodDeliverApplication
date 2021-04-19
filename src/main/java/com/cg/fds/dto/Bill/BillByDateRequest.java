package com.cg.fds.dto.Bill;

import javax.validation.constraints.NotBlank;

public class BillByDateRequest {
	@NotBlank (message="start-Date cannot be null")
	private String startDate;
	@NotBlank (message="end-Date cannot be null")
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
