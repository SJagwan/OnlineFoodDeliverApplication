package com.cg.fds.repository;

import java.util.List;

import com.cg.fds.entities.Category;

public interface ICategoryRepository {

	public Category addCategory(Category cat);
	public Category updateCategory(Category cat);
	public Category removeCategory(Category cat);
	public Category viewCategory(Category cat);
	public List<Category> viewAllCategory();
}
