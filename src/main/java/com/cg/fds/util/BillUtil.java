package com.cg.fds.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fds.dto.Bill.BillDetailsResponse;
import com.cg.fds.entities.Bill;

@Component
public class BillUtil {
	@Autowired
	private DateUtil dateUtil;

	public BillDetailsResponse toDetails(Bill bill) {
		BillDetailsResponse details = new BillDetailsResponse();

		details.setBillId(bill.getBillId());
		details.setOrderStatus(bill.getOrder().getOrderStatus());
		String dateText = dateUtil.toText(bill.getBillDate());
		details.setBillDate(dateText);
		details.setTotalItem(bill.getTotalItem());
		details.setTotalCost(bill.getTotalCost());
		return details;
	}

	public Bill getBill() {
		return new Bill();
	}

	public List<BillDetailsResponse> toDetailsList(List<Bill> bills) {
		List<BillDetailsResponse> bd = new ArrayList<>();
		for (Bill bill : bills) {
			bd.add(toDetails(bill));
		}
		return bd;
	}

}
