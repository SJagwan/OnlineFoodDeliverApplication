package com.cg.fds.exception;

public class CartItemNotFoundException extends RuntimeException {
	public CartItemNotFoundException(String msg)
	{
		super(msg);
	}

}
