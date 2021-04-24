package com.cg.fds.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.cg.fds.dto.foodcart.FoodCartDetailResponse;
import com.cg.fds.dto.foodcart.FoodCartDetails;
import com.cg.fds.entities.CartItem;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.service.ICartService;
import com.cg.fds.service.IItemService;

import com.cg.fds.util.FoodCartUtil;

@Validated
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
	public FoodCartDetailResponse addItemToCart(@RequestBody @Valid FoodCartRequest request) {
		FoodCart cart = cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item = itemService.viewItem(request.getItemId());
		cart=cartService.addItemToCart(cart, item);
		CartItem cartItem = cartService.findCartItem(cart, item);
		FoodCartDetailResponse response=new FoodCartDetailResponse(item.getItemId(), cartItem.getQuantity(),item.getCost());
		return response;
	}

	@PutMapping("/increasequantity")
	public FoodCartDetailResponse increaseQuantity(@RequestBody @Valid ChangeQuantityRequest request) {
		FoodCart cart = cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item = itemService.viewItem(request.getItemId());
		cart=cartService.increaseQuantity(cart, item,request.getQuantity());
		CartItem cartItem = cartService.findCartItem(cart, item);
		FoodCartDetailResponse response=new FoodCartDetailResponse(item.getItemId(), cartItem.getQuantity(),item.getCost());
		return response;
	}

	@PutMapping("/reducequantity")
	public FoodCartDetailResponse reduceQuantity(@RequestBody @Valid ChangeQuantityRequest request) {
		FoodCart cart = cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item = itemService.viewItem(request.getItemId());
		cart=cartService.reduceQuantity(cart, item,request.getQuantity());
		CartItem cartItem = cartService.findCartItem(cart, item);
		FoodCartDetailResponse response=new FoodCartDetailResponse(item.getItemId(), cartItem.getQuantity(),item.getCost());
		return response;
	}

	@DeleteMapping("/removeitem")
	public FoodCartDetails removeItem(@RequestBody @Valid FoodCartRequest request) {
		FoodCart cart = cartService.findFoodCartByCustomer(request.getCustomerId());
		Item item = itemService.viewItem(request.getItemId());
		return cartUtil.toCartDetails(cartService.removeItem(cart, item));
	}

	@Transactional
	@DeleteMapping("/clear")
	public FoodCartDetails clearCart(@RequestBody @Valid ClearCartRequest request) {
		FoodCart cart = cartService.findFoodCartByCustomer(request.getCustomerId());
		return cartUtil.toCartDetails(cartService.clearCart(cart));
	}

	@GetMapping("/find")
	public FoodCartDetails findCart(@RequestBody @Valid FindCartRequest request) {
		return cartUtil.toCartDetails(cartService.findFoodCartByCustomer(request.getCustomerId()));

	}

}
