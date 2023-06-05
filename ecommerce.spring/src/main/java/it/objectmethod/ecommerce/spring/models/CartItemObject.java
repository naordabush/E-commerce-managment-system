package it.objectmethod.ecommerce.spring.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItemObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// this is a foreign key
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartObject cart;

	// this is a foreign key
	@ManyToOne
	@JoinColumn(name = "item_id")
	private ItemObject item;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "order_time")
	private Date orderTime;

	// getter and setter>

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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}