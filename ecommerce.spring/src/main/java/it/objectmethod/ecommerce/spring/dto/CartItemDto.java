package it.objectmethod.ecommerce.spring.dto;

import java.util.Date;

import it.objectmethod.ecommerce.spring.models.CartObject;
import it.objectmethod.ecommerce.spring.models.ItemObject;

public class CartItemDto {
	private Long id;
	private CartObject cart;
	private ItemObject item;
	private Integer quantity;
	private Date orderTime;

	// getter and setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CartObject getCart() {
		return cart;
	}

	public void setCart(CartObject cart) {
		this.cart = cart;
	}

	public ItemObject getItem() {
		return item;
	}

	public void setItem(ItemObject item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the orderTime
	 */
	public Date getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}
