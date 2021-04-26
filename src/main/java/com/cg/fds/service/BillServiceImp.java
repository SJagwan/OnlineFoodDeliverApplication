package com.cg.fds.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.BillDoesNotExistException;
import com.cg.fds.exception.InvalidBillException;
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

	/**
	 * scenario : Adding the bill to the database, Calculating the Bill amount
	 * input: Bill Object is passed in the parameter expectation: Bill should get
	 * added to database
	 */
	@Override
	public Bill addBill(Bill bill) {
		validateBill(bill);
		LocalDateTime currentDateTime = currentDateTime();
		bill.setBillDate(currentDateTime);
		OrderDetails order = bill.getOrder();
		double totalCost = 0;
		List<Item> items = order.getItems();
		for (Item item : items) {
			totalCost = totalCost + (item.getCost() * item.getQuantity());
		}
		bill.setTotalItem(items.size());
		bill.setTotalCost(totalCost);
		return billRepository.save(bill);
	}

	/**
	 * scenario : Updating the Bill input: Bill Object is passed in the parameter
	 * expectation: If the bill is present in the Database, then bill is getting
	 * updated, or else an exception is thrown
	 */

	@Override
	public Bill updateBill(Bill bill) {
		validateBill(bill);
		int billId = bill.getBillId();
		boolean exist = billRepository.existsById(billId);
		if (!exist) {
			throw new BillDoesNotExistException("Bill doesn't exist for id =" + bill.getBillId());
		}
		return billRepository.save(bill);
	}

	/**
	 * scenario : Removing the Bill input: Bill Object is passed in the parameter
	 * expectation: If the bill is present in the Database, then bill is getting
	 * removed, or else an exception is thrown
	 */

	@Override
	public Bill removeBill(Bill bill) {
		validateBill(bill);
		int billId = bill.getBillId();
		boolean exist = billRepository.existsById(billId);
		if (!exist) {
			throw new BillDoesNotExistException("Bill doesn't exist for id =" + bill.getBillId());
		}

		billRepository.delete(bill);
		return bill;
	}

	/**
	 * scenario : Viewing the Bill input: Bill ID is passed in the parameter
	 * expectation: If the bill is present in the Database, then bill is getting
	 * viewed, or else an exception is thrown
	 */

	@Override
	public Bill viewBill(int billId) {
		Optional<Bill> viewBill = billRepository.findById(billId);
		if (!viewBill.isPresent()) {
			throw new BillDoesNotExistException("Bill doesn't exist for id =" + billId);
		}
		return viewBill.get();
	}

	/**
	 * scenario : Viewing the Bill between the LocalDates input: Passing StartDate
	 * and EndDate in the parameter expectation: Getting Bills between all the
	 * LocalDates
	 */

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) {
		LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
		List<Bill> bills = billRepository.findOrdersBetweenDates(startDateTime, endDateTime);
		if(bills.isEmpty()) {
			throw new  BillDoesNotExistException("Bill doesn't exist for =" + startDate + " to " + endDate);
		}
		return bills;

	}

	/**
	 * scenario : Viewing the Bill input: Passing Customer Id in the parameter
	 * expectation: Getting List of Bills By Customer Id
	 */

	@Override
	public List<Bill> viewBills(String custId) {
		Customer customer = customerRepository.findCustomerByCustomerId(custId);
		FoodCart cart = cartRepository.findFoodCartByCustomer(customer);
		OrderDetails orderDetail = orderRepository.findOrderDetailsByCart(cart);
		return billRepository.findByOrder(orderDetail);
	}

	/**
	 * scenario : Total Cost input: Bill Object is passed in the parameter
	 * expectation: Getting the Total Cost
	 */
	

	@Override
	public double totalCost(Bill bill) {
		return bill.getTotalCost();
	}

	/**
	 * scenario : If Bill is null input: Bill Object is passed in the parameter
	 * expectation: Throws an Error
	 */

	public void validateBill(Bill bill) {
		if (bill == null) {
			throw new InvalidBillException("Bill cannot be null");
		}
	}

}
