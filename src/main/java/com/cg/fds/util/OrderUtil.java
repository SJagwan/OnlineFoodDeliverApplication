package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.OrderDetails;

@Component
public class OrderUtil {
	
	public OrderDetails getOrderDeatils() {
		return new OrderDetails();
	}

}
