package com.cg.fds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.AddOrderException;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.exception.OrderNotFoundException;
import com.cg.fds.exception.RemoveOrderException;
import com.cg.fds.exception.UpdateOrderException;
import com.cg.fds.repository.IBillRepository;
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.IOrderRepository;
import com.cg.fds.util.BillUtil;

@Service
public class OrderServiceImp implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IBillService billService;

	@Autowired
	private ICartRepository cartRepository;
	
	@Autowired
	private ICartItemRepository cartItemRepository;
	
	@Autowired
	private IBillRepository billRepository;
	
	@Autowired
	private BillUtil billUtil;

	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}

	@Transactional
	@Override
	public OrderDetails addOrder(OrderDetails order) {
		validateOrder(order);
		FoodCart cart = order.getCart();
		List<Item> items = cartItemRepository.findItemsByCart(cart);
		if (items == null || items.isEmpty()) {
			throw new AddOrderException("order can't be created because cart is empty");
		}
		order.setItems(items);
		order.setOrderDate(currentDateTime());
		order.setOrderStatus(OrderStatus.CREATED);
		order = orderRepository.save(order);
		
		cartService.clearCart(cart);
		Bill bill=billUtil.getBill();
		bill.setOrder(order);
		billService.addBill(bill);
		
		return order;
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		validateOrder(order);
		int orderId = order.getOrderId();
		boolean exist = orderRepository.existsById(orderId);
		if (!exist) {
			throw new UpdateOrderException("Order doesn't exist for id =" + order.getOrderId());
		}
		return orderRepository.save(order);
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		validateOrder(order);
		int orderId=order.getOrderId();
		boolean exist = orderRepository.existsById(orderId);
		if (!exist) {
			throw new RemoveOrderException("Order doesn't exist for id =" + order.getOrderId());
		}
		Bill bill=billRepository.findBillByOrder(order);
		billRepository.delete(bill);
		orderRepository.delete(order);
		return order;
	}
	

	
	@Override
	public OrderDetails viewOrder(int orderId) {
		Optional<OrderDetails> optionOrderDetail = orderRepository.findById(orderId);
		if (!optionOrderDetail.isPresent()) {
			throw new OrderNotFoundException("order not found for id=" + orderId);
		}
		return optionOrderDetail.get();
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant resName) {
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		FoodCart cart=cartRepository.findFoodCartByCustomer(customer);
		List<OrderDetails> orderList = orderRepository.findByCart(cart);
		return orderList;

	}

	public void validateOrder(OrderDetails order) {
		if (order == null) {
			throw new InvalidOrderException("OrderDetail Cannot be null");
		}

	}

}
