package com.cg.fds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Category;
import com.cg.fds.exception.CategoryNotFoundException;
import com.cg.fds.exception.InvalidCategoryException;
import com.cg.fds.exception.InvalidCategoryIdException;
import com.cg.fds.exception.InvalidCategoryNameException;
import com.cg.fds.exception.RemoveCategoryException;
import com.cg.fds.exception.UpdateCategoryException;
import com.cg.fds.repository.ICategoryRepository;

@Service
public class CategoryServiceImp implements ICategoryService {
	@Autowired
	private ICategoryRepository categoryRepository;
	
	/**
	 * scenario : Adding category
	 * input: Cat Object is passed in the parameter
	 * expectation: Saving and adding category
	 */

	@Override
	public Category addCategory(Category cat) {
		validateCategory(cat);
		return categoryRepository.save(cat);
	}
	/**
	 * scenario : Updating the Category
	 * input: Cat Object is passed in the parameter
	 * expectation: If the Category is present in the Database, then Category is getting updated, or else an exception is thrown
	 */

	@Override
	public Category updateCategory(Category cat) {
		validateCategory(cat);
		boolean exists = categoryRepository.existsById(cat.getCatId());
		if (!exists) {
			throw new UpdateCategoryException("Category with id not present=" + cat.getCatId());
		}
		return categoryRepository.save(cat);
	}
	/**
	 * scenario : Removing the Category
	 * input: Cat Object is passed in the parameter
	 * expectation: If the Category is present in the Database, then Category is getting removed , or else an exception is thrown
	 */
	@Override
	public Category removeCategory(Category cat) {
		validateCategory(cat);
		boolean exists = categoryRepository.existsById(cat.getCatId());
		if (!exists) {
			throw new RemoveCategoryException("Category with id not present=" + cat.getCatId());
		}
		categoryRepository.delete(cat);
		return cat;
	}
	/**
	 * scenario : Viewing the Category
	 * input: Cat Object is passed in the parameter
	 * expectation: If the Category is present in the Database, then Category is getting viewed, or else an exception is thrown
	 */
	@Override
	public Category viewCategory(Category cat) {
		validateCategory(cat);
		Optional<Category> viewCategory = categoryRepository.findById(cat.getCatId());
		if (!viewCategory.isPresent()) {
			throw new CategoryNotFoundException("Category with id not present=" + cat.getCatId());
		}
		return viewCategory.get();
	}
	/**
	 * scenario : Viewing the list of all categories
	 * expectation: View the list
	 */
	@Override
	public List<Category> viewAllCategory() {
		return categoryRepository.findAll();
	}

	/**
	 * scenario : Validate Category
	 * input: cat Object is passed in the parameter
	 * expectation: If the category is null, an exception is thrown
	 */
	void validateCategory(Category cat) {
		if (cat == null) {
			throw new InvalidCategoryException("Category can't be null");
		}
		validateCategoryName(cat.getCategoryName());
		validateCategoryId(cat.getCatId());
	}

	/**
	 * scenario : Validate category name
	 * input: categoryName Object is passed in the parameter
	 * expectation: If the category Name is null, an exception is thrown
	 */
	void validateCategoryName(String categoryName) {
		if (categoryName == null || categoryName.isEmpty() || categoryName.trim().isEmpty()) {
			throw new InvalidCategoryNameException("Category Name can't be null or empty");
		}
	}

	/**
	 * scenario : Validating Category Id
	 * input: catId Object is passed in the parameter
	 * expectation: If the Category Id is null, an exception is thrown
	 */
	void validateCategoryId(String catId) {
		if (catId == null || catId.isEmpty() || catId.trim().isEmpty()) {
			throw new InvalidCategoryIdException("Category Id can't be null or empty");
		}
	}
}
