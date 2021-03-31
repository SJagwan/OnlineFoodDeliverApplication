package com.cg.fds.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.foodcart.FoodCartRequest;
import com.cg.fds.dto.foodcart.ChangeQuantityRequest;
import com.cg.fds.dto.foodcart.ClearCartRequest;
import com.cg.fds.dto.foodcart.FindCartRequest;
import com.cg.fds.dto.foodcart.FoodCartDetails;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.service.ICartService;
import com.cg.fds.service.IItemService;
import com.cg.fds.util.FoodCartUtil;

@RequestMapping("/foodcart")
@RestController
public class FoodCartController {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private FoodCartUtil cartUtil;
	
	@Autowired
	private IItemService itemService;
	
	@PostMapping("/additemtocart")
	public FoodCartDetails addItemToCart(@RequestBody FoodCartRequest request)
	{
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item=itemService.viewItem(request.getItemId());
		return cartUtil.toCartDetails(cartService.addItemToCart(cart, item));
	}
	
	@PutMapping("/increasequantity")
	public FoodCartDetails increaseQuantity(@RequestBody ChangeQuantityRequest request)
	{
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item=itemService.viewItem(request.getItemId());
		return cartUtil.toCartDetails(cartService.increaseQuantity(cart, item, request.getQuantity()));
	}
	
	@PutMapping("/reducequantity")
	public FoodCartDetails reduceQuantity(@RequestBody ChangeQuantityRequest request)
	{
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item=itemService.viewItem(request.getItemId());
		return cartUtil.toCartDetails(cartService.reduceQuantity(cart, item, request.getQuantity()));
	}
	
	@DeleteMapping("/removeitem")
	public FoodCartDetails removeItem(@RequestBody FoodCartRequest request)
	{
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item=itemService.viewItem(request.getItemId());
		return cartUtil.toCartDetails(cartService.removeItem(cart, item));
	}
	@Transactional
	@DeleteMapping("/clearcart")
	public FoodCartDetails clearCart(@RequestBody ClearCartRequest request)
	{
		FoodCart cart=cartService.findFoodCartByCustomer(request.getCustomerId());
		return cartUtil.toCartDetails(cartService.clearCart(cart));
	}
	
	@GetMapping("/findcart")
	public FoodCartDetails findCart(@RequestBody FindCartRequest request)
	{
		return cartUtil.toCartDetails(cartService.findFoodCartByCustomer(request.getCustomerId()));
	}

}
