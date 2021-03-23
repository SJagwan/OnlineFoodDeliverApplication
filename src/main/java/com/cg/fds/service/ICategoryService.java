package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Category;

public interface ICategoryService {

	public Category addCategory(Category cat);
	public Category updateCategory(Category cat);
	public Category removeCategory(Category cat);
	public Category viewCategory(Category cat);
	public List<Category> viewAllCategory();
}
