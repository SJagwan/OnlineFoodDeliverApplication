package com.cg.fds.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.BillDoesNotException;
import com.cg.fds.exception.InvalidBillException;
import com.cg.fds.exception.RemoveBillException;
import com.cg.fds.exception.UpdateBillException;
import com.cg.fds.repository.IBillRepository;


@Service 
public class BillServiceImp implements IBillService{
	
	@Autowired
	private IBillRepository billRepository;
	
	@Autowired
	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}

	@Override
	public Bill addBill(Bill bill) {
		validateBill(bill);
		LocalDateTime currentDateTime = currentDateTime();
		bill.setBillDate(currentDateTime);
		OrderDetails order = bill.getOrder();
		double totalCost=0;
		List<Item>items = order.getItems();
				for(Item item:items){
			totalCost=totalCost+item.getCost();
		}
		bill.setTotalItem(items.size());
		bill.setTotalCost(totalCost);
		return billRepository.save(bill);
	}

	@Override
	public Bill updateBill(Bill bill) {
		 validateBill (bill);
		 int billId=bill.getBillId();
			boolean exist=billRepository.existsById(1);
			if(!exist)
			{
				throw new UpdateBillException("Bill doesn't exist for id =" + bill.getBillId());
			}
		 return billRepository.save(bill);
	}

	@Override
	public Bill removeBill(Bill bill) {
		validateBill(bill);
		int billId=bill.getBillId();
		boolean exist=billRepository.existsById(1);
		if(!exist)
		{
			throw new RemoveBillException("Bill doesn't exist for id =" + bill.getBillId());
		}
		Bill removeBill = billRepository.remove(bill);
		return removeBill;
	}

	@Override
	public Bill viewBill(Bill bill) {
		int id =1;
		Optional<Bill> viewBill = billRepository.findById(id);
		if(!viewBill.isPresent()) {
			throw new BillDoesNotException("Bill doesn't exist for id =" + bill.getBillId());
		}
		return viewBill.get();
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) {
		
		return null;
	}

	@Override
	public List<Bill> viewBills(String custId) {
		
		return null;
	}
	
	@Override
	public double totalCost(Bill bill) {
		return bill.getTotalCost();
	}
	 

	public void validateBill(Bill bill) {
		if (bill== null) {
			throw new InvalidBillException("Bill cannot be null");
		}
	}
	
	

}
