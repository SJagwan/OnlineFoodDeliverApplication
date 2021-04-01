package com.cg.fds.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.Bill.AddBillRequest;
import com.cg.fds.dto.Bill.BillByDateRequest;
import com.cg.fds.dto.Bill.BillDetailsResponse;
import com.cg.fds.dto.Bill.BillUpdateRequest;
import com.cg.fds.dto.Bill.BillRequest;
import com.cg.fds.entities.Bill;
import com.cg.fds.service.IBillService;
import com.cg.fds.util.BillUtil;
import com.cg.fds.util.DateUtil;

@Validated
@RequestMapping("/bills")
@RestController
public class BillController {

	@Autowired
	private IBillService billService;

	@Autowired
	private BillUtil billUtil;

	@Autowired
	private DateUtil dateUtil;

	@GetMapping(value = "/get/{id}")
	public BillDetailsResponse fetchBillDetails(@PathVariable("id") @NotNull int id) {
		Bill bill = billService.viewBill(id);
		return billUtil.toDetails(bill);
	}

	@PostMapping("/add")
	public BillDetailsResponse addBill(@RequestBody @Valid AddBillRequest requestData) {
		Bill bill = billService.viewBill(requestData.getBillId());
		Bill addedBill = billService.addBill(bill);
		return billUtil.toDetails(addedBill);
	}

	@PutMapping("/update")
	public BillDetailsResponse BillUpdate(@RequestBody @Valid BillUpdateRequest requestData) {
		Bill bill = billService.viewBill(requestData.getBillId());
		bill.setTotalCost(requestData.getTotalCost());
		bill.setTotalItem(requestData.getTotalItem());
		Bill updatedBill = billService.updateBill(bill);
		return billUtil.toDetails(updatedBill);
	}

	@GetMapping("/getByDate")
	public List<BillDetailsResponse> fetchBillDetailsByDate(@RequestBody @Valid BillByDateRequest requestData) {
		LocalDate start = dateUtil.toLocalDate(requestData.getStartDate());
		LocalDate end = dateUtil.toLocalDate(requestData.getEndDate());
		List<Bill> bill = billService.viewBills(start, end);
		return billUtil.toDetailsList(bill);
	}

	@DeleteMapping("/delete")
	public BillDetailsResponse deleteBill(@RequestBody @Valid BillRequest requestData) {
		Bill bill = billService.viewBill(requestData.getBillId());
		Bill deletedBill = billService.removeBill(bill);
		return billUtil.toDetails(deletedBill);
	}

	@GetMapping("/viewbycustomer/{id}")
	public List<BillDetailsResponse> viewBills(@PathVariable @NotBlank String id) {
		List<Bill> bill = billService.viewBills(id);
		return billUtil.toDetailsList(bill);
	}

	@GetMapping("/totalcost")
	public double getTotalCost(@RequestBody @Valid BillRequest requestData) {
		Bill bill = billService.viewBill(requestData.getBillId());
		return billService.totalCost(bill);
	}

}