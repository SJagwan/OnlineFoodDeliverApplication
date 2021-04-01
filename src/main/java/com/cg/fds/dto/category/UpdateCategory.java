package com.cg.fds.dto.category;

import javax.validation.constraints.NotBlank;

public class UpdateCategory {
	

	@NotBlank
	private String id;
	@NotBlank
	private String name;
	
	public UpdateCategory() {

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
