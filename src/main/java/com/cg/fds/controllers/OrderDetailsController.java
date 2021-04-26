package com.cg.fds.controllers;

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

import com.cg.fds.dto.orderDetails.AddOrderRequest;
import com.cg.fds.dto.orderDetails.ViewOrDeleteOrderRequest;
import com.cg.fds.dto.orderDetails.OrderDetailsResponse;
import com.cg.fds.dto.orderDetails.UpdateOrderRequest;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.CustomerServiceImp;
import com.cg.fds.service.IOrderService;
import com.cg.fds.util.OrderUtil;

@Validated
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

	@PostMapping("/add")
	public OrderDetailsResponse addOrder(@RequestBody @Valid AddOrderRequest request) {
		OrderDetails order = new OrderDetails();
		FoodCart cart = cartService.findFoodCartByCustomer(request.getCustomerId());
		order.setCart(cart);
		return orderUtil.toOrderDetailsResponse(orderService.addOrder(order));
	}

	@PutMapping("/update")
	public OrderDetailsResponse updateOrder(@RequestBody @Valid UpdateOrderRequest request) {
		OrderDetails order = orderService.viewOrder(request.getOrderId());
		order.setOrderStatus(request.getOrderStatus());
		return orderUtil.toOrderDetailsResponse(orderService.updateOrder(order));
	}

	@DeleteMapping("/delete")
	public OrderDetailsResponse deleteOrder(@RequestBody @Valid ViewOrDeleteOrderRequest request) {
		OrderDetails order = orderService.viewOrder(request.getOrderId());
		return orderUtil.toOrderDetailsResponse(orderService.removeOrder(order));
	}

	@GetMapping("/view/{id}")
	public OrderDetailsResponse viewOrder(@PathVariable @NotNull(message="orderId cannot be not") Integer id ) {
		return orderUtil.toOrderDetailsResponse(orderService.viewOrder(id));
	}

	@GetMapping("/viewbycustomerid/{id}")
	public List<OrderDetailsResponse> viewAllOrderByCustomer(@PathVariable @NotBlank(message="customerId cannot be null") String id) {

		Customer customer = customerService.viewCustomer(id);
		return orderUtil.toOrderDetailsResponseList(orderService.viewAllOrders(customer));
	}
	

}
