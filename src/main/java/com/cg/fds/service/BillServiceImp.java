package com.cg.fds.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Bill;
import com.cg.fds.repository.IBillRepository;


@Service 
public class BillServiceImp implements IBillService{
	
	@Autowired
	private IBillRepository billRepository;

	@Override
	public Bill addBill(Bill bill) {
		return billRepository.save(bill);
	}

	@Override
	public Bill updateBill(Bill bill) {
		
		 return billRepository.save(bill);
	}

	@Override
	public Bill removeBill(Bill bill) {
		validateBill(bill);
		Bill removeBill = billRepository.remove(bill);
		return removeBill;
	}

	@Override
	public Bill viewBill(Bill bill) {
		Optional<Bill> viewBill = billRepository.findById(bill);
		Bill newBill = null;
		if(viewBill.isPresent()) {
			newBill = viewBill.get();
		}
		return bill;
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> viewBills(String custId) {
		
		return null;
	}
	
	@Override
	public double totalCost(Bill bill) {
		return billRepository.totalCost(bill);
	}
	 

	

}
