package com.cg.fds.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.fds.entities.Bill;

public interface IBillService {

	 Bill addBill(Bill bill);
	 Bill updateBill(Bill bill);
	 Bill removeBill(Bill bill);
	 Bill viewBill(int bill);
	 List<Bill> viewBills(LocalDate startDate,LocalDate endDate);
	 List<Bill> viewBills(String custId);
	 double totalCost(Bill bill);
}
