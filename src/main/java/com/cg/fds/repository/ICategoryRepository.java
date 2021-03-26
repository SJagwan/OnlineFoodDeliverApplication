package com.cg.fds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category,String> {


//	Category save(Category cat);
//	Category remove(Category cat);
//	boolean existsById(String catId);
//    Optional<Category> findById(String id);
	/**
	* public Category addCategory(Category cat);
	* public Category updateCategory(Category cat);
	* public Category removeCategory(Category cat);
	* public Category viewCategory(Category cat);
	* public List<Category> viewAllCategory();
    */
}
