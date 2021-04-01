package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Category;

public interface ICategoryService {

	 Category addCategory(Category cat);
	 Category updateCategory(Category cat);
	 Category viewCategory(String cat);
	 List<Category> viewAllCategory();
}
