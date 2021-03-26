package com.cg.fds.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	private String catId;
	private String categoryName;

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
