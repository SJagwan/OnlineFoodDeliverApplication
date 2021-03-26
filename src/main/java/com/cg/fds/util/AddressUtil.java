package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Address;

@Component
public class AddressUtil {
	public Address getAddress() {
		return new Address();
	}
}
