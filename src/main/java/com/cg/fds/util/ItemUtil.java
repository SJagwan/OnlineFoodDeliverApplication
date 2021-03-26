package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Item;

@Component
public class ItemUtil {
	
	public Item getItem() {
		return new Item();
	}

}
