package com.cg.fds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.BillDoesNotException;
import com.cg.fds.exception.InvalidBillException;
import com.cg.fds.exception.RemoveBillException;
import com.cg.fds.exception.UpdateBillException;
import com.cg.fds.repository.IBillRepository;



@ExtendWith(MockitoExtension.class)
public class BillServiceImpUnitTest {
	@Mock
	IBillRepository billRepository;
	@Spy
	@InjectMocks
	BillServiceImp billService;
	
	
	/*
	 * Scenario adding item and order in bill
	 */
	
	@Test
	public void addBillTest_1() {
		Bill bill = Mockito.mock(Bill.class);
		Bill billSaved = Mockito.mock(Bill.class);
		Mockito.doNothing().when(billService).validateBill(bill);
		Item item1 = new Item();
		item1.setCost(5);
		Item item2 = new Item();
		item2.setCost(10);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		OrderDetails order = mock(OrderDetails.class);
		when(order.getItems()).thenReturn(items);
		when(bill.getOrder()).thenReturn(order);
		Mockito.when(billRepository.save(bill)).thenReturn(billSaved);
		Bill result = billService.addBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(billSaved, result);
		verify(billService).validateBill(bill);
		verify(bill).setTotalItem(2);
		verify(bill).setTotalCost(15);
		verify(billRepository).save(bill);
		
	}
	
	
	
	/*
	 * Scenario updating Bill with no exception
	 */
	
	@Test
	public void updateBillTest_1() {
		int id=1;
		Bill bill = Mockito.mock(Bill.class);
		Bill billSaved = Mockito.mock(Bill.class);
		Mockito.doNothing().when(billService).validateBill(bill);
		Mockito.when(bill.getBillId()).thenReturn(id);
		Mockito.when(billRepository.existsById(id)).thenReturn(true);
		Mockito.when(billRepository.save(bill)).thenReturn(billSaved);
		Bill result = billService.updateBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(billSaved, result);
		Mockito.verify(billRepository).existsById(1);

	}
	
	/*
	 * Scenario updating bill with exception
	 */
	
	@Test
	public void updateBillTest_2() {
		int id=1;
		Bill bill = Mockito.mock(Bill.class);
		Mockito.doNothing().when(billService).validateBill(bill);
		Mockito.when(bill.getBillId()).thenReturn(id);
		Mockito.when(billRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> billService.updateBill(bill);
		Assertions.assertThrows(UpdateBillException.class, executable);
		Mockito.verify(billRepository).existsById(1);

	}
	/*
	 * Scenario removing bill without exception
	 */
	
	@Test
	public void removeBillTest_1() {
		int id=1;
		Bill bill = Mockito.mock(Bill.class);
		Mockito.doNothing().when(billService).validateBill(bill);
		Mockito.when(bill.getBillId()).thenReturn(id);
		Mockito.when(billRepository.existsById(id)).thenReturn(true);
		Bill result = billService.removeBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(bill, result);
		Mockito.verify(billRepository).existsById(1);

	}
	
	/*
	 * Scenario removing bill with exception
	 */
	
	@Test
	public void removeBillTest_2() {
		int id=1;
		Bill bill = Mockito.mock(Bill.class);
		Mockito.doNothing().when(billService).validateBill(bill);
		Mockito.when(bill.getBillId()).thenReturn(id);
		Mockito.when(billRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> billService.removeBill(bill);
		Assertions.assertThrows(RemoveBillException.class, executable);
		Mockito.verify(billRepository).existsById(1);

	}
	
	/*
	 * Scenario : viewing bill without exception
	 */
	
	@Test
	public void viewOrderTest_1() {
		Bill bill=Mockito.mock(Bill.class);
		int id=1;
		Optional<Bill>optionBill=Optional.of(bill);
		Mockito.when(bill.getBillId()).thenReturn(id);
		Mockito.when(billRepository.findById(id)).thenReturn(optionBill);
		Bill result=billService.viewBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(bill,result);
		Mockito.verify(billRepository).findById(1);
	}
	/*
	 * Scenario : viewing bill with exception
	 */
	
	@Test
	public void viewOrderTest_2() {
		int id=1;
		Bill bill=Mockito.mock(Bill.class);
		Optional<Bill>optionBill=Optional.empty();
		Mockito.when(bill.getBillId()).thenReturn(id);
		Mockito.when(billRepository.findById(id)).thenReturn(optionBill);
		Executable executable = () -> billService.viewBill(bill);
		Assertions.assertThrows(BillDoesNotException.class, executable);		
	}
	
	/*
	 * Scenario : adding the cost of items in the order and get total cost
	 */
	
	
	@Test
	public void totalCostTest_1() {
		Bill bill = Mockito.mock(Bill.class);
		Mockito.when(bill.getTotalCost()).thenReturn(15.0);
		double result = billService.totalCost(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(15,result);
		
	}
	/*
	 * Scenario : validating the bill 
	 */
	
	@Test
	public void validateBill_1() {
		Bill bill = null;
		Executable executable = () -> billService.validateBill(bill);
		Assertions.assertThrows(InvalidBillException.class, executable);
	}

}
