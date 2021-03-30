package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.fds.dto.category.CategoryDetails;
import com.cg.fds.entities.Category;

@Component
public class CategoryUtil {
	
	public Category getCategory() {
		return new Category();
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
public CategoryDetails toCategoryDetail(Category category) {
	CategoryDetails cd = new CategoryDetails();
	cd.setName(category.getCategoryName());
	return cd;
}
public List<CategoryDetails> toCategoryDetailsList(List<Category> categoryList){
	List<CategoryDetails>categoryDetails = new ArrayList<>();
	for(Category c:categoryList) {
		categoryDetails.add(toCategoryDetail(c));
	}
	return categoryDetails;
}
}
