package com.cg.fds.exception;

public class BillDoesNotExistException extends RuntimeException {
	public BillDoesNotExistException(String msg) {
		super(msg);
	}
}
