package com.cg.fds.dto.category;

import javax.validation.constraints.NotBlank;

public class AddCategory {
	public AddCategory() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	private String name;

}
