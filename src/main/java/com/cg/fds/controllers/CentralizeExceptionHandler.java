package com.cg.fds.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.fds.exception.AddOrderException;
import com.cg.fds.exception.BillDoesNotExistException;
import com.cg.fds.exception.CategoryNotFoundException;
import com.cg.fds.exception.CustomerNotFoundException;
import com.cg.fds.exception.InvalidBillException;
import com.cg.fds.exception.InvalidCartException;
import com.cg.fds.exception.InvalidCategoryException;
import com.cg.fds.exception.InvalidCustomerException;
import com.cg.fds.exception.InvalidItemException;
import com.cg.fds.exception.InvalidOrderException;
import com.cg.fds.exception.InvalidRestaurantException;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.exception.OrderNotFoundException;
import com.cg.fds.exception.RestaurantNotFoundException;

@RestControllerAdvice
public class CentralizeExceptionHandler {

	/**
	 * scenario : Catching Exception when bill is invalid
	 */

	@ExceptionHandler(InvalidBillException.class)
	public String invalidBillException(InvalidBillException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception when cart is invalid
	 */

	@ExceptionHandler(InvalidCartException.class)
	public String invalidCartException(InvalidCartException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception when category is invalid
	 */
	@ExceptionHandler(InvalidCategoryException.class)
	public String invalidCategoryException(InvalidCategoryException e) {
		return e.getMessage();
	}

	@ExceptionHandler(InvalidCustomerException.class)
	public String invalidCustomerException(InvalidCustomerException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception when item is invalid
	 */

	@ExceptionHandler(InvalidItemException.class)
	public String invalidItemException(InvalidItemException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception when order is invalid
	 */
	@ExceptionHandler(InvalidOrderException.class)
	public String invalidOrderException(InvalidOrderException e) {
		return e.getMessage();
	}

	@ExceptionHandler(InvalidRestaurantException.class)
	public String invalidRestaurantException(InvalidRestaurantException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception when cart is null and creating order
	 */

	@ExceptionHandler(AddOrderException.class)
	public String addOrderException(AddOrderException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception, when bill is not present in database
	 */

	@ExceptionHandler(BillDoesNotExistException.class)
	public String billDoesNotExistException(BillDoesNotExistException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception, when category is not present in database
	 */

	@ExceptionHandler(CategoryNotFoundException.class)
	public String categoryNotFoundException(CategoryNotFoundException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception, when customer is not present in database
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	public String customerNotFoundException(CustomerNotFoundException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception, when item is not present in database
	 */
	@ExceptionHandler(ItemNotFoundException.class)
	public String itemNotFoundException(ItemNotFoundException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception, when order is not present in database
	 */
	@ExceptionHandler(OrderNotFoundException.class)
	public String orderNotFoundException(OrderNotFoundException e) {
		return e.getMessage();
	}

	/**
	 * scenario : Catching Exception, when item is not present in database
	 */
	@ExceptionHandler(RestaurantNotFoundException.class)
	public String restaurantNotFoundException(RestaurantNotFoundException e) {
		return e.getMessage();
	}
}
