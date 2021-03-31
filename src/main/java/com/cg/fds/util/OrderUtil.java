package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;

@Component
public class OrderUtil {
	
	@Autowired
	private DateUtil dateUtil;
	
	public OrderDetails getOrderDeatils() {
		return new OrderDetails();
	}
	
	

}
