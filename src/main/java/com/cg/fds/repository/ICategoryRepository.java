package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category,String> {

//	public Category addCategory(Category cat);
//	public Category updateCategory(Category cat);
//	public Category removeCategory(Category cat);
//	public Category viewCategory(Category cat);
//	public List<Category> viewAllCategory();
}
