package com.cg.fds.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Bill;
import com.cg.fds.entities.OrderDetails;

public interface IBillRepository extends JpaRepository<Bill,Integer> {

	List<Bill> findByorder(OrderDetails order);
}
