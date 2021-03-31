package com.cg.fds.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.BillDoesNotException;
import com.cg.fds.exception.InvalidBillException;
import com.cg.fds.exception.RemoveBillException;
import com.cg.fds.exception.UpdateBillException;
import com.cg.fds.repository.IBillRepository;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.ICustomerRepository;
import com.cg.fds.repository.IOrderRepository;

@Service
public class BillServiceImp implements IBillService {

	@Autowired
	private IBillRepository billRepository;
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private ICartRepository cartRepository;
	
	@Autowired
	private IOrderRepository orderRepository;

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
		double totalCost = 0;
		List<Item> items = order.getItems();
		for (Item item : items) {
			totalCost = totalCost + (item.getCost()*item.getQuantity());
		}
		bill.setTotalItem(items.size());
		bill.setTotalCost(totalCost);
		return billRepository.save(bill);
	}

	@Override
	public Bill updateBill(Bill bill) {
		validateBill(bill);
		int billId = bill.getBillId();
		boolean exist = billRepository.existsById(billId);
		if (!exist) {
			throw new UpdateBillException("Bill doesn't exist for id =" + bill.getBillId());
		}
		return billRepository.save(bill);
	}

	@Override
	public Bill removeBill(Bill bill) {
		validateBill(bill);
		int billId = bill.getBillId();
		boolean exist = billRepository.existsById(billId);
		if (!exist) {
			throw new RemoveBillException("Bill doesn't exist for id =" + bill.getBillId());
		}
		billRepository.delete(bill);
		return bill;
	}

	@Override
	public Bill viewBill(Bill bill) {
		int billId = bill.getBillId();
		Optional<Bill> viewBill = billRepository.findById(billId);
		if (!viewBill.isPresent()) {
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
		Customer customer=customerRepository.findCustomerByCustomerId(custId);
		FoodCart cart=cartRepository.findFoodCartByCustomer(customer);
		OrderDetails orderDetail=orderRepository.findOrderDetailsByCart(cart);
		return billRepository.findByOrder(orderDetail);
	}

	@Override
	public double totalCost(Bill bill) {
		return bill.getTotalCost();
	}

	public void validateBill(Bill bill) {
		if (bill == null) {
			throw new InvalidBillException("Bill cannot be null");
		}
	}

}
