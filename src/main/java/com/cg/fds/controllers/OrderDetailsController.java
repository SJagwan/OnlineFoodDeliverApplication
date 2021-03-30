package com.cg.fds.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.orderDetails.AddOrderRequest;
import com.cg.fds.dto.orderDetails.ViewOrDeleteOrderRequest;
import com.cg.fds.dto.orderDetails.OrderDetailsResponse;
import com.cg.fds.dto.orderDetails.UpdateOrderRequest;
import com.cg.fds.dto.orderDetails.ViewAllByCustomerIdRequest;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.CustomerServiceImp;
import com.cg.fds.service.IOrderService;
import com.cg.fds.util.OrderUtil;



@RequestMapping("/orders")
@RestController
public class OrderDetailsController {
	
	@Autowired
    private IOrderService orderService;
	
	@Autowired
	private CartServiceImp cartService;
	
	@Autowired
	private CustomerServiceImp customerService;
	
	@Autowired
	private OrderUtil orderUtil;
	
	@PostMapping("/addorder")
	public OrderDetailsResponse addOrder(@RequestBody AddOrderRequest request) {
		OrderDetails order=new OrderDetails();
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		order.setCart(cart);
		return orderUtil.toOrderDetailsResponse(orderService.addOrder(order));
	}
	
	@PutMapping("/updateorder")
	public OrderDetailsResponse updateOrder(@RequestBody UpdateOrderRequest request) {
		OrderDetails order=orderService.viewOrder(request.getOrderId());
		order.setOrderStatus(request.getOrderStatus());
		return orderUtil.toOrderDetailsResponse(orderService.updateOrder(order));
	}
	
	@DeleteMapping("/deleteorder")
	public OrderDetailsResponse deleteOrder(@RequestBody ViewOrDeleteOrderRequest request) {
		OrderDetails order=orderService.viewOrder(request.getOrderId());
		return orderUtil.toOrderDetailsResponse(orderService.removeOrder(order));
	}

	@GetMapping("/vieworder")
	public OrderDetailsResponse viewOrder(@RequestBody ViewOrDeleteOrderRequest request) {
		return orderUtil.toOrderDetailsResponse(orderService.viewOrder(request.getOrderId()));
	}
	
	@GetMapping("/vieworderbycustomerid")
	public List<OrderDetailsResponse> viewAllOrderByCustomer(@RequestBody ViewAllByCustomerIdRequest request) {
		
		Customer customer=customerService.viewCustomer(request.getCustomerId());
		return orderUtil.toOrderDetailsResponseList(orderService.viewAllOrders(customer));
	}

}
