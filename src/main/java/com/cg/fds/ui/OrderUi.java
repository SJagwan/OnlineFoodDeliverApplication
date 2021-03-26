package com.cg.fds.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.OrderServiceImp;
import com.cg.fds.util.FoodCartUtil;
import com.cg.fds.util.ItemUtil;
import com.cg.fds.util.OrderUtil;

@Component
public class OrderUi {
	
	@Autowired
	private OrderUtil orderUtil;
	
	@Autowired
	private FoodCartUtil cartUtil;
	
	@Autowired
	private ItemUtil itemUtil;
	
	
	
	@Autowired
	private OrderServiceImp orderService;
	
	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}
	
	public void start() {
		
		FoodCart cart=cartUtil.getFoodCart();

		cart.setCartId(generateId());
		
		Item item1=itemUtil.getItem();
		item1.setItemId(generateId());
		item1.setCost(10);
		item1.setItemName("lays");
		item1.setQuantity(1);
		item1.setCategory(null);
		
		Item item2=itemUtil.getItem();
		item2.setItemId(generateId());
		item2.setCost(100);
		item1.setItemName("Amul");
		item1.setQuantity(2);
		item2.setCategory(null);
		
		List<Item>items=new ArrayList<>();
		items.add(item1);
		items.add(item2);
		cart.setItemList(items);
		
		OrderDetails order=orderUtil.getOrderDeatils();
		order.setItems(cart.getItemList());
		
		System.out.println("------------------ UI -----------------");
		System.out.println(orderService.addOrder(order));
		
		
		
		
		
	}

}
