package com.cg.fds.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.exception.CategoryNotFoundException;
import com.cg.fds.exception.InvalidCategoryException;
import com.cg.fds.exception.InvalidCategoryIdException;
import com.cg.fds.exception.InvalidCategoryNameException;
import com.cg.fds.exception.RemoveCategoryException;
import com.cg.fds.exception.UpdateCategoryException;
import com.cg.fds.repository.ICategoryRepository;
import com.cg.fds.repository.IItemRepository;

@Service
public class CategoryServiceImp implements ICategoryService {
	
	@Autowired
	private IItemRepository itemRepository;
	@Autowired
	private ICategoryRepository categoryRepository;
	
	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}

	@Override
	public Category addCategory(Category cat) {
		validateCategory(cat);
		String id = generateId();
		cat.setCatId(id);
		return categoryRepository.save(cat);
	}

	@Override
	public Category updateCategory(Category cat) {
		validateCategory(cat);
		boolean exists = categoryRepository.existsById(cat.getCatId());
		if (!exists) {
			throw new UpdateCategoryException("Category with id not present=" + cat.getCatId());
		}
		return categoryRepository.save(cat);
	}

//	@Override
//	public Category removeCategory(Category cat) {
//		validateCategory(cat);
//		boolean exists = categoryRepository.existsById(cat.getCatId());
//		if (!exists) {
//			throw new RemoveCategoryException("Category with id not present=" + cat.getCatId());
//		}
//		List<Item> items = itemRepository.findByCategory(cat);
//		for(Item item:items) {
//			itemRepository.delete(item);
//		}
//		categoryRepository.delete(cat);
//		return cat;
//	}


	@Override
     public Category viewCategory(String catId) {
		Optional<Category> viewCategory = categoryRepository.findById(catId);
		if (!viewCategory.isPresent()) {
			throw new CategoryNotFoundException("Category with id not present=" + catId);
		}
		return viewCategory.get();
	}

	@Override
	public List<Category> viewAllCategory() {
		return categoryRepository.findAll();
	}

	/**
	 * Function to validate Category
	 */
	void validateCategory(Category cat) {
		if (cat == null) {
			throw new InvalidCategoryException("Category can't be null");
		}
		validateCategoryName(cat.getCategoryName());
		validateCategoryId(cat.getCatId());
	}

	/**
	 * Function to validate Category Name
	 */
	void validateCategoryName(String categoryName) {
		if (categoryName == null || categoryName.isEmpty() || categoryName.trim().isEmpty()) {
			throw new InvalidCategoryNameException("Category Name can't be null or empty");
		}
	}

	/**
	 * Function to validate Category ID
	 */
	void validateCategoryId(String catId) {
		if (catId == null || catId.isEmpty() || catId.trim().isEmpty()) {
			throw new InvalidCategoryIdException("Category Id can't be null or empty");
		}
	}
}
