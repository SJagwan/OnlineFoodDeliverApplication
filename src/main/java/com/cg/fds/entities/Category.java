package com.cg.fds.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	private String catId;
	private String categoryName;
	
}
