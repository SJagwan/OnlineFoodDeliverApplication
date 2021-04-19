package com.cg.fds.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.category.AddCategory;
import com.cg.fds.dto.category.CategoryDetails;
import com.cg.fds.dto.category.UpdateCategory;
import com.cg.fds.entities.Category;
import com.cg.fds.service.ICategoryService;
import com.cg.fds.util.CategoryUtil;

@Validated
@RequestMapping("/category")
@RestController
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private CategoryUtil categoryUtil;

	@PostMapping("/add")
	public CategoryDetails addingCategory(@RequestBody @Valid AddCategory request) {
		Category category = categoryUtil.getCategory();
		category.setCategoryName(request.getName());
		category = categoryService.addCategory(category);
		return categoryUtil.toCategoryDetail(category);
	}

	@PutMapping("/update")
	public CategoryDetails updatingCategory(@RequestBody @Valid UpdateCategory request) {
		Category category = categoryService.viewCategory(request.getId());
		category.setCategoryName(request.getName());
		category = categoryService.updateCategory(category);
		return categoryUtil.toCategoryDetail(category);
	}


	@GetMapping("/view/{id}")
	public CategoryDetails viewingCategory(@PathVariable @NotBlank(message="Category Id cannot be null") String id) {
		Category category = categoryService.viewCategory(id);
		return categoryUtil.toCategoryDetail(category);
	}

	@GetMapping("/viewAll")
	public List<CategoryDetails> viewingAllCategory() {
		List<Category> category = categoryService.viewAllCategory();
		return categoryUtil.toCategoryDetailsList(category);
	}

}
