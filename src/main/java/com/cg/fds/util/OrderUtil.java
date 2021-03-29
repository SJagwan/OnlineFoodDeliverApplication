package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.fds.dto.orderDetails.OrderDetailsR;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;

@Component
public class OrderUtil {
	
	public OrderDetails getOrderDeatils() {
		return new OrderDetails();
	}
	
	public OrderDetailsR toOrderDetailsR(OrderDetails order)
	{
		OrderDetailsR odr=new OrderDetailsR();
		List<String>itemName=new ArrayList<>();
		
		odr.setCustomerId(order.getCart().getCustomer().getCustomerId());
		odr.setFirstName(order.getCart().getCustomer().getFirstName());
		odr.setOrderStatus(order.getOrderStatus());
		odr.setOrderDate(order.getOrderDate());
		for(Item item:order.getItems())
		{
			itemName.add(item.getItemName());
		}
		odr.setItemName(itemName);
		return odr;
	}

}
