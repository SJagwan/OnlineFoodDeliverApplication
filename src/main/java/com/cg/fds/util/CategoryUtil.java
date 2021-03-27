package com.cg.fds.util;

import org.springframework.stereotype.Component;

import com.cg.fds.entities.Category;

@Component
public class CategoryUtil {
	
	public Category getCategory() {
		return new Category();
	}

}
