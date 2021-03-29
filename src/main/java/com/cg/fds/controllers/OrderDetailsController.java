package com.cg.fds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.orderDetails.AddOrderRequest;
import com.cg.fds.dto.orderDetails.OrderDetailsR;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.IOrderService;
import com.cg.fds.util.FoodCartUtil;
import com.cg.fds.util.OrderUtil;



@RequestMapping("/order/orderDetails")
@RestController
public class OrderDetailsController {
	
	@Autowired
    private IOrderService orderService;
	
	@Autowired
	private CartServiceImp cartService;
	@Autowired
	private OrderUtil orderUtil;
	
	@PostMapping("/addOrder")
	public OrderDetailsR addOrder(@RequestBody AddOrderRequest request) {
		OrderDetails order=new OrderDetails();
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		order.setCart(cart);
		return orderUtil.toOrderDetailsR(orderService.addOrder(order));
	}

}
