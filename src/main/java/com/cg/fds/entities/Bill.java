package com.cg.fds.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Bill {
	
	private int billId;
	private OrderDetails order;
	private int totalItem;
	private double totalCost;
	LocalDateTime billDate;
}
