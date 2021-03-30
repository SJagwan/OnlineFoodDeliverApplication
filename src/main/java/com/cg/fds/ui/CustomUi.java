package com.cg.fds.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Address;
import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Category;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.service.BillServiceImp;
import com.cg.fds.service.CartServiceImp;
import com.cg.fds.service.CategoryServiceImp;
import com.cg.fds.service.CustomerServiceImp;
import com.cg.fds.service.ItemServiceImp;
import com.cg.fds.service.OrderServiceImp;
import com.cg.fds.service.RestaurantServiceImp;
import com.cg.fds.util.AddressUtil;
import com.cg.fds.util.BillUtil;
import com.cg.fds.util.CategoryUtil;
import com.cg.fds.util.CustomerUtil;
import com.cg.fds.util.FoodCartUtil;
import com.cg.fds.util.ItemUtil;
import com.cg.fds.util.OrderUtil;
import com.cg.fds.util.RestaurantUtil;

@Component
public class CustomUi {
	
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
	private BillUtil billUtil;
	
	@Autowired
	private RestaurantUtil restaurantUtil;
	
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
	
	@Autowired
	private BillServiceImp billService;
	
	@Autowired
	private RestaurantServiceImp restaurantService;
	
	@Autowired
	private ICartRepository cartRepository;
	
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
		
		Address address3=addressUtil.getAddress();
		address3.setAddressId(generateId());
		address3.setArea("dehradun");
		
		Customer customer1=customerUtil.getCustomer();
		customer1.setMobileNumber("843999912w");
		customer1.setFirstName("Shubhanshu");
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
		
		
		Restaurant r1=restaurantUtil.getRestaurant();
		r1.setRestaurantId(generateId());
		r1.setRestaurantName("Taj");
		r1.setAddress(address3);
		r1.setContactNumber("123456789");
		List<Item>items=items=new ArrayList<>();
		items.add(item1);
		items.add(item2);
		r1.setItemList(items);
		
		
		OrderDetails order=orderUtil.getOrderDeatils();
		
		
		
		
		System.out.println("------------------UI-- Customer -----------------");
		System.out.println("Adding Customer");
		System.out.println(customerService.addCustomer(customer1));
		System.out.println(customerService.addCustomer(customer2));
//		System.out.println("Removing Customer");
//		System.out.println(customerService.removeCustomer(customer2));
//		System.out.println("Update Customer");
//		customer2.setAge("20");
//		customer2.setFirstName("Hardik");
//		System.out.println(customerService.updateCustomer(customer2));
//
		FoodCart cart1=cartRepository.findFoodCartByCustomer(customer1);
		cart1.setCustomer(customer1);
		
		FoodCart cart2=cartRepository.findFoodCartByCustomer(customer2);
		cart2.setCustomer(customer2);
		
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
//		System.out.println("Find all category");
//		System.out.println(categoryService.viewAllCategory());
		
		
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
		
		System.out.println("------------------UI-- Restaurant -----------------");
		System.out.println("Adding Restaurant");
		System.out.println(restaurantService.addRestaurant(r1));
//		System.out.println(r1.getItemList());
//		System.out.println("Remove Restaurant");
//		System.out.println(restaurantService.removeRestaurant(r1));
//		System.out.println("Update Restaurant");
//		r1.setRestaurantName("SJ");
//		System.out.println(restaurantService.updateRestaurant(r1));
//		System.out.println("View Restaurant");
//		System.out.println(restaurantService.viewRestaurant(r1.getRestaurantId()));
//		System.out.println("FindBy location Restaurant");
//		System.out.println(restaurantService.viewNearByRestaurant("dehradun"));
		System.out.println();
		
		
		System.out.println("------------------UI-- FoodCart -----------------");
		
		System.out.println("Adding ItemToCart");
		System.out.println(cartService.addItemToCart(cart1, item1));

//		System.out.println(cart.getItemList());
		
		System.out.println(cartService.addItemToCart(cart1, item2));
		System.out.println(cartService.addItemToCart(cart2, item3));
//		System.out.println(" Increase Quantity");
//		System.out.println(cartService.increaseQuantity(cart, item1, 3));
//		System.out.println(" Reduce Quantity");
//		System.out.println(cartService.reduceQuantity(cart, item1, 3));
//		System.out.println(" Remove Item");
//		System.out.println(cartService.removeItem(cart, item2));
////		System.out.println("Clear cart");
////		System.out.println(cartService.clearCart(cart));
////		System.out.println();
//		
		order.setCart(cart1);
		System.out.println("------------------UI-- OrderDetails -----------------");
		
		System.out.println("Adding orderDetail");
		System.out.println(orderService.addOrder(order));
//		System.out.println("Update orderDetail");
//		order.setOrderStatus(OrderStatus.DELIVERED);
////		System.out.println(orderService.updateOrder(order));
//		System.out.println("Remove orderDetail");
//		System.out.println(orderService.removeOrder(order));
////		System.out.println("View orderDetail");
////		System.out.println(orderService.viewOrder(order.getOrderId()));
////		System.out.println((order.getItems()));
//		
//		System.out.println();
	
//		System.out.println("------------------UI-- Bill -----------------");
		
//		System.out.println("Add bill");
//		System.out.println(billService.addBill(bill));
//		System.out.println("Update bill");
//		bill.setTotalItem(6);
//		System.out.println(billService.updateBill(bill));
//		System.out.println("View bill");
//		System.out.println(billService.viewBill(bill));
//		System.out.println("TotalCost bill");
//		System.out.println(billService.totalCost(bill));
//		
//		System.out.println("View bill by customerId");
//		System.out.println(billService.viewBills(customer1.getCustomerId()));
		
//		System.out.println("remove bill");
//		System.out.println(billService.removeBill(bill));
		
		
		
		
		
		
	}

}
