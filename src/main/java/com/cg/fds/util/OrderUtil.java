package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fds.dto.orderDetails.OrderDetailsResponse;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;

@Component
public class OrderUtil {
	
	@Autowired
	private DateUtil dateUtil;
	
	public OrderDetails getOrderDeatils() {
		return new OrderDetails();
	}
	
	public OrderDetailsResponse toOrderDetailsResponse(OrderDetails order)
	{
		OrderDetailsResponse odr=new OrderDetailsResponse();
		List<String>itemName=new ArrayList<>();	
		odr.setCustomerId(order.getCart().getCustomer().getCustomerId());
		odr.setFirstName(order.getCart().getCustomer().getFirstName());
		odr.setOrderStatus(order.getOrderStatus());
		String dateText=dateUtil.toText(order.getOrderDate());
		odr.setOrderDate(dateText);
		for(Item item:order.getItems())
		{
			itemName.add(item.getItemName());
		}
		odr.setItemName(itemName);
		return odr;
	}
	public List<OrderDetailsResponse> toOrderDetailsResponseList(List<OrderDetails> orders)
	{
		List<OrderDetailsResponse>OrderDetailsResponses=new ArrayList<>();
		for(OrderDetails order:orders)
		{
			OrderDetailsResponses.add(toOrderDetailsResponse(order));
		}
		return OrderDetailsResponses;
	}

}
