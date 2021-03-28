package com.cg.fds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.IOrderService;



@RequestMapping("/order/orderDetails")
@RestController
public class OrderDetailsController {
	
	@Autowired
    private IOrderService orderService;
	
	@PostMapping("/addOrder")
	public OrderDetails addOrder(@RequestBody OrderDetails request) {
		return orderService.addOrder(request);
	}

}
