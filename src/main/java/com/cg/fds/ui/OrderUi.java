package com.cg.fds.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Address;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.CustomerServiceImp;
import com.cg.fds.service.ItemServiceImp;
import com.cg.fds.service.OrderServiceImp;
import com.cg.fds.util.AddressUtil;
import com.cg.fds.util.CustomerUtil;
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
	private CustomerUtil customerUtil;
	
	@Autowired
	private AddressUtil addressUtil;
	
	@Autowired
	private CartServiceImp cartService;
	
	@Autowired
	private OrderServiceImp orderService;
	
	@Autowired
	private ItemServiceImp itemService;
	
	@Autowired
	private CustomerServiceImp customerService;
	
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
		
//		FoodCart cart=cartUtil.getFoodCart();
//		cart.setCartId(generateId());
		
		Address address=addressUtil.getAddress();
		address.setAddressId(generateId());
		
		Customer customer=customerUtil.getCustomer();
		customer.setMobileNumber("12234566");
		customer.setAddress(address);
		
		Item item1=itemUtil.getItem();
		item1.setItemId(generateId());
		item1.setCost(10);
		item1.setItemName("lays");
		item1.setQuantity(1);
		item1.setCategory(null);
		
		Item item2=itemUtil.getItem();
		item2.setItemId(generateId());
		item2.setCost(100);
		item2.setItemName("Amul");
		item2.setQuantity(1);
		item2.setCategory(null);
		
		
		OrderDetails order=orderUtil.getOrderDeatils();
		
		System.out.println("------------------UI-- Customer -----------------");
		System.out.println("Adding Customer");
		customer=customerService.addCustomer(customer);
		System.out.println(customer);
		
		
		
		
		System.out.println("------------------ UI--Item -----------------");
		System.out.println("Adding Item");
		System.out.println(itemService.addItem(item1));
		System.out.println(itemService.addItem(item2));
//		System.out.println("Searching Item");
//		System.out.println(itemService.viewItem(item1.getItemId()));
//		System.out.println("Updating Item");
//		item1.setCost(200);
//		System.out.println(itemService.updateItem(item1));
//		System.out.println("Remove Item");
//		System.out.println(itemService.removeItem(item2.getItemId()));
//		System.out.println();
		
		
//		System.out.println("------------------UI-- FoodCart -----------------");
//		System.out.println("Adding ItemToCart");
//		System.out.println(cartService.addItemToCart(cart, item1));
//		System.out.println(cartService.addItemToCart(cart, item2));
//		System.out.println(" Increase Quantiy");
//		System.out.println(cartService.increaseQuantity(cart, item1, 3));
//		System.out.println(" Reduce Quantiy");
//		System.out.println(cartService.reduceQuantity(cart, item1, 2));
////		System.out.println(" Remove Item");
////		System.out.println(cartService.removeItem(cart, item2));
////		System.out.println("Clear cart");
////		System.out.println(cartService.clearCart(cart));
//		System.out.println();
//		
//		order.setCart(cart);
//		System.out.println("------------------UI-- OrderDetails -----------------");
//		System.out.println("Adding orderDetail");
//		System.out.println(orderService.addOrder(order));
//		System.out.println("Update orderDetail");
//		order.setOrderStatus(OrderStatus.DELIVERED);
//		System.out.println(orderService.updateOrder(order));
////		System.out.println("Remove orderDetail");
////		System.out.println(orderService.removeOrder(order));
////		System.out.println("View orderDetail");
////		System.out.println(orderService.viewOrder(order.getOrderId()));
////		System.out.println((order.getItems()));
		
		System.out.println();
		
		
		
		
		
		
	}

}
