package com.cg.fds.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Address;
import com.cg.fds.entities.Category;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.CategoryServiceImp;
import com.cg.fds.service.CustomerServiceImp;
import com.cg.fds.service.ItemServiceImp;
import com.cg.fds.service.OrderServiceImp;
import com.cg.fds.util.AddressUtil;
import com.cg.fds.util.CategoryUtil;
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
	private CategoryUtil categoryUtil;
	
	@Autowired
	private CartServiceImp cartService;
	
	@Autowired
	private OrderServiceImp orderService;
	
	@Autowired
	private ItemServiceImp itemService;
	
	@Autowired
	private CategoryServiceImp categoryService;
	
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
		
		FoodCart cart=cartUtil.getFoodCart();
		cart.setCartId(generateId());
		
		Category category1=categoryUtil.getCategory();
		category1.setCatId(generateId());
		category1.setCategoryName("chips");
		
		Category category2=categoryUtil.getCategory();
		category2.setCatId(generateId());
		category2.setCategoryName("Chocolate");
		
		Address address1=addressUtil.getAddress();
		address1.setAddressId(generateId());
		
		Address address2=addressUtil.getAddress();
		address2.setAddressId(generateId());
		
		Customer customer1=customerUtil.getCustomer();
		customer1.setMobileNumber("843999912w");
		customer1.setAddress(address1);
		
		Customer customer2=customerUtil.getCustomer();
		customer2.setMobileNumber("70180293893");
		customer2.setAddress(address2);
		
		Item item1=itemUtil.getItem();
		item1.setItemId(generateId());
		item1.setCost(10);
		item1.setItemName("lays");
		item1.setQuantity(1);
		item1.setCategory(category1);
		
		Item item2=itemUtil.getItem();
		item2.setItemId(generateId());
		item2.setCost(100);
		item2.setItemName("Amul");
		item2.setQuantity(1);
		item2.setCategory(category2);
		
		Item item3=itemUtil.getItem();
		item3.setItemId(generateId());
		item3.setCost(100);
		item3.setItemName("lays");
		item3.setQuantity(1);
		item3.setCategory(category1);
		
		
		OrderDetails order=orderUtil.getOrderDeatils();
//		
//		System.out.println("------------------UI-- Customer -----------------");
//		System.out.println("Adding Customer");
//		System.out.println(customerService.addCustomer(customer1));
//		System.out.println(customerService.addCustomer(customer2));
////		System.out.println("Removing Customer");
////		System.out.println(customerService.removeCustomer(customer2));
//		System.out.println("Update Customer");
//		customer2.setAge("20");
//		customer2.setFirstName("Shubhanshu");
//		
//		System.out.println(customerService.updateCustomer(customer2));
		
//		System.out.println("View Customer");
//		System.out.println(customerService.viewCustomer(customer1.getCustomerId()));
		
		System.out.println("------------------ UI--Category -----------------");
		System.out.println("Adding category");
		System.out.println(categoryService.addCategory(category1));
		System.out.println(categoryService.addCategory(category2));
//		System.out.println("Update category");
//		category1.setCategoryName("colddrink");
//		System.out.println(categoryService.updateCategory(category1));
//		System.out.println("Remove category");
//		System.out.println(categoryService.removeCategory(category1));
//		System.out.println("View  category");
//		System.out.println(categoryService.viewCategory(category2));
		System.out.println("Find all category");
		System.out.println(categoryService.viewAllCategory());
		
		
		System.out.println("------------------ UI--Item -----------------");
		System.out.println("Adding Item");
		System.out.println(itemService.addItem(item1));
		System.out.println(itemService.addItem(item2));
		System.out.println(itemService.addItem(item3));
//		System.out.println("Searching Item");
//		System.out.println(itemService.viewItem(item1.getItemId()));
//		System.out.println("Updating Item");
//		item1.setCost(200);
//		System.out.println(itemService.updateItem(item1));
//		System.out.println("Remove Item");
//		System.out.println(itemService.removeItem(item2.getItemId()));
//		System.out.println();
//		System.out.println("View Item by name");
//		System.out.println(itemService.viewAllItemsByName("lays"));
		
		
		System.out.println("------------------UI-- FoodCart -----------------");
		System.out.println("Adding ItemToCart");
		System.out.println(cartService.addItemToCart(cart, item1));
		System.out.println(cartService.addItemToCart(cart, item2));
		System.out.println(" Increase Quantiy");
		System.out.println(cartService.increaseQuantity(cart, item1, 3));
		System.out.println(" Reduce Quantiy");
		System.out.println(cartService.reduceQuantity(cart, item1, 2));
//		System.out.println(" Remove Item");
//		System.out.println(cartService.removeItem(cart, item2));
//		System.out.println("Clear cart");
//		System.out.println(cartService.clearCart(cart));
//		System.out.println();
		
		order.setCart(cart);
		System.out.println("------------------UI-- OrderDetails -----------------");
		System.out.println("Adding orderDetail");
		System.out.println(orderService.addOrder(order));
		System.out.println("Update orderDetail");
		order.setOrderStatus(OrderStatus.DELIVERED);
		System.out.println(orderService.updateOrder(order));
//		System.out.println("Remove orderDetail");
//		System.out.println(orderService.removeOrder(order));
//		System.out.println("View orderDetail");
//		System.out.println(orderService.viewOrder(order.getOrderId()));
//		System.out.println((order.getItems()));
		
		System.out.println();
		
		
		
		
		
		
	}

}
