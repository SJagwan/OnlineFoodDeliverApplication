package com.cg.fds.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Bill;

public interface IBillRepository extends JpaRepository<Bill,Integer> {

	
}
