package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Bill;

@Component
public class BillUtil {
	public Bill getBill() {
		return new Bill();
	}
	

}
