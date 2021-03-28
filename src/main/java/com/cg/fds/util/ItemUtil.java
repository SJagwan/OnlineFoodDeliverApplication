package com.cg.fds.util;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Item;

@Component
public class ItemUtil {
	
	public Item getItem() {
		return new Item();
	}
	
	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}

}
