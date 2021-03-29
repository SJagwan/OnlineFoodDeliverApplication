package com.cg.fds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.Items.AddItem;
import com.cg.fds.entities.Item;
import com.cg.fds.service.IItemService;
import com.cg.fds.util.ItemUtil;

@RequestMapping("/items")
@RestController
public class ItemController {
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private ItemUtil itemUtil;
	
	
	@PostMapping("/add_item")
	public Item addItem(@RequestBody AddItem request)
	{
		Item item=itemUtil.getItem();
		item.setCategory(request.getCategory());
		item.setCost(request.getCost());
		item.setItemId(itemUtil.generateId());
		item.setItemName(request.getItemName());
		item.setQuantity(request.getQuantity());
		return itemService.addItem(item);
		
	}

	
	      
}
