package com.cg.fds.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Customer;
import com.cg.fds.repository.IBillRepository;
@ExtendWith(MockitoExtension.class)
public class BillServiceImpTest {
	@Mock
	IBillRepository billRepository;
	@Spy
	@InjectMocks
	BillServiceImp billService;
	
	@Test
	public void addBillTest_1() {
		Bill bill = Mockito.mock(Bill.class);
		Bill billSaved = Mockito.mock(Bill.class);
		Mockito.when(billRepository.save(bill)).thenReturn(billSaved);
		Bill result = billService.addBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(billSaved, result);
		
		
	}
	
	/*
	 * Scenario updating Bill to null 
	 */
	
	
	@Test
	public void updateBillTest_1() {
		Bill result = billService.updateBill(null);
		Assertions.assertEquals(null, result);
	}
	
	/*
	 * Scenario updating Bill to billService
	 */
	
	@Test
	public void removeBillTest_1() {
		Bill bill = Mockito.mock(Bill.class);
		Bill billSaved = Mockito.mock(Bill.class);
		Mockito.when(billRepository.remove(bill)).thenReturn(billSaved);
		Bill result = billService.removeBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(billSaved, result);
	}
	
	@Test
	public void updateBillTest_2() {
		Bill bill = Mockito.mock(Bill.class);
		Bill billSaved = Mockito.mock(Bill.class);
		Mockito.when(billRepository.save(bill)).thenReturn(billSaved);
		Bill result = billService.updateBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(billSaved, result);

	}
	
	
	
	
	@Test
	public void viewBillTest_1() {
		Bill bill = Mockito.mock(Bill.class);
		Optional<Bill> optionalSaved = Optional.of(bill);
		Mockito.when(billRepository.findById(bill)).thenReturn(optionalSaved);
		Bill result = billService.viewBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(bill, result);
	}

}
