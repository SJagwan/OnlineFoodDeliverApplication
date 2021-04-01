package com.cg.fds.dto.Items;

import javax.validation.constraints.NotBlank;

public class FindItemByCategory {
	@NotBlank
	private String catId;

	public FindItemByCategory() {

	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

}
