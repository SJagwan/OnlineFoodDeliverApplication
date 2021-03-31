package com.cg.fds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.FoodCart.AddFoodCart;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.service.ICartService;
import com.cg.fds.util.FoodCartUtil;

@RequestMapping("/foodcart")
@RestController
public class FoodCartController {
	@Autowired
	private ICartService foodCartServices;
	@Autowired
	private FoodCartUtil foodCartUtil;
	
	@PostMapping("/add_foodcart")
	public FoodCart addFoodCart(@RequestBody AddFoodCart request){
	FoodCart foodCart = new FoodCart();
	
	return foodCart;
	}

}
