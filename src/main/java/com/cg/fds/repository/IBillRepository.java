package com.cg.fds.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.fds.entities.Bill;
import com.cg.fds.entities.OrderDetails;

public interface IBillRepository extends JpaRepository<Bill,Integer> {

	List<Bill> findByOrder(OrderDetails order);
	Bill findBillByOrder(OrderDetails order);
	@Query("from Bill where billDate between :startDate and :endDate")
	List<Bill>findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
}
