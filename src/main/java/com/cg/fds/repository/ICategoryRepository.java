package com.cg.fds.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category,String> {
	
	

}
