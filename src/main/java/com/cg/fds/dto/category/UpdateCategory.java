package com.cg.fds.dto.category;

import javax.validation.constraints.NotBlank;

public class UpdateCategory {
	

	@NotBlank(message="Category id cannot be null/blank")
	private String id;
	@NotBlank(message="Category name cannot be blank")
	private String name;
	
	public UpdateCategory() {
		// Do Nothing
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
