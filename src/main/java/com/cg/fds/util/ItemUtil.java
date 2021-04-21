package com.cg.fds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.fds.dto.Items.ItemDetails;
import com.cg.fds.entities.Item;

@Component
public class ItemUtil {

	public Item getItem() {
		return new Item();
	}

	public ItemDetails toItemDetails(Item item) {
		ItemDetails id = new ItemDetails();
		id.setCategoryName(item.getCategory().getCategoryName());
		id.setCost(item.getCost());
		id.setQuantity(item.getQuantity());
		id.setItemName(item.getItemName());
		id.setItemId(item.getItemId());
		return id;
	}

	public List<ItemDetails> toItemDetailsList(List<Item> list) {
		List<ItemDetails> result = new ArrayList<>();
		for (Item item : list) {
			result.add(toItemDetails(item));
		}
		return result;
	}

	public String generateId() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomNum = random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}

}
