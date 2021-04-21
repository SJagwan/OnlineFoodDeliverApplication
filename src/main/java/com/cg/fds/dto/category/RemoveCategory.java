package com.cg.fds.dto.category;

import javax.validation.constraints.NotBlank;

public class RemoveCategory {

	@NotBlank(message="Category id cannot be null/blank")
	private String id;
	
	public RemoveCategory() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
