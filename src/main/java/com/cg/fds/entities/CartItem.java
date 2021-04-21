
package com.cg.fds.entities;

import javax.persistence.*;

@Table(name = "cart_item")
@Entity
public class CartItem {

	@Id
	private String id;

	@JoinColumn(name = "cart")
	@ManyToOne
	private FoodCart cart;

	@JoinColumn(name = "item")
	@ManyToOne
	private Item item;

	private int quantity;

	public CartItem() {

	}

	public static String id(FoodCart cart, Item item) {
		return cart.getCartId() + "-" + item.getItemId() + "-ci";

	}

	public CartItem(FoodCart cart, Item item, int quantity) {
		this.cart = cart;
		this.item = item;
		this.quantity = quantity;
		this.id = id(cart, item);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FoodCart getCart() {
		return cart;
	}

	public void setCart(FoodCart cart) {
		this.cart = cart;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
