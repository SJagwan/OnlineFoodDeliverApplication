package com.cg.fds.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Bill;

public interface IBillRepository extends JpaRepository<Bill,Integer> {

	//public Bill save(Bill bill);
	//public Bill remove(Bill bill);
	//public Optional<Bill> findById(Bill bill);
	//public double totalCost(Bill bill);
	//public Bill updateBill(Bill bill);
	//public Bill removeBill(Bill bill);
	//public Bill viewBill(Bill bill);
	//public List<Bill> viewBills(LocalDate startDate,LocalDate endDate);
	//public List<Bill> viewBills(String custId);
	
}
