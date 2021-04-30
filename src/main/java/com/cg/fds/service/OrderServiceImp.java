package com.cg.fds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.constant.OrderStatus;
import com.cg.fds.entities.Bill;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exception.AddOrderException;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.exception.OrderNotFoundException;

import com.cg.fds.repository.IBillRepository;
import com.cg.fds.repository.ICartItemRepository;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.repository.IOrderRepository;
import com.cg.fds.util.BillUtil;

@Service
public class OrderServiceImp implements IOrderService {
	
	private static final Logger Log=LoggerFactory.getLogger(OrderServiceImp.class);

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
		Log.info("will return the current time");
		return LocalDateTime.now();
	}

	/**
	 * scenario : Adding the Order input: order Object is passed in the parameter
	 * expectation: Order should be added. If the order is not added, an exception
	 * is thrown.
	 */
	@Transactional
	@Override
	public OrderDetails addOrder(OrderDetails order) {
		Log.info("inside add order , this will create order and bill");
		validateOrder(order);
		FoodCart cart = order.getCart();
		List<Item> items = cartItemRepository.findItemsByCart(cart);
		if (items.isEmpty()) {
			throw new AddOrderException("order can't be created because cart is empty");
		}
		order.setItems(items);
		order.setOrderDate(currentDateTime());
		order.setOrderStatus(OrderStatus.CREATED);
		order = orderRepository.save(order);

		cartService.clearCart(cart);
		Bill bill = billUtil.getBill();
		bill.setOrder(order);
		billService.addBill(bill);

		return order;
	}

	/**
	 * scenario : Updating the Order input: order Object is passed in the parameter
	 * expectation: If the order is present in the Database, then order is getting
	 * updated, or else an exception is thrown
	 */
	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		Log.info("this will update the status of order");
		validateOrder(order);
		int orderId = order.getOrderId();
		boolean exist = orderRepository.existsById(orderId);
		if (!exist) {
			throw new OrderNotFoundException("Order doesn't exist for id =" + order.getOrderId());
		}
		return orderRepository.save(order);
	}

	/**
	 * scenario : Removing the Order input: order Object is passed in the parameter
	 * expectation: If the order is present in the Database, then order is getting
	 * removed, or else an exception is thrown
	 */

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		validateOrder(order);
		int orderId = order.getOrderId();
		boolean exist = orderRepository.existsById(orderId);
		if (!exist) {
			throw new OrderNotFoundException("Order doesn't exist for id =" + order.getOrderId());
		}
		Bill bill = billRepository.findBillByOrder(order);
		billRepository.delete(bill);
		orderRepository.delete(order);
		return order;
	}

	/**
	 * scenario : viewing the Order input: orderId Object is passed in the parameter
	 * expectation: If the orderId is not present in the Database, an exception is
	 * thrown
	 */

	@Override
	public OrderDetails viewOrder(int orderId) {
		Optional<OrderDetails> optionOrderDetail = orderRepository.findById(orderId);
		if (!optionOrderDetail.isPresent()) {
			throw new OrderNotFoundException("order not found for id=" + orderId);
		}
		return optionOrderDetail.get();
	}



	/**
	 * scenario : viewing the list of all orders of the customer input: customer
	 * Object is passed in the parameter expectation: List of all orders should be
	 * returned
	 */

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		Log.info("view all orders , of a particular customer");
		FoodCart cart = cartRepository.findFoodCartByCustomer(customer);
		List<OrderDetails> orderList = orderRepository.findByCart(cart);
		return orderList;

	}

	public void validateOrder(OrderDetails order) {
		if (order == null) {
			throw new InvalidOrderException("OrderDetail Cannot be null");
		}

	}

}
