package it.objectmethod.ecommerce.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// this is a foreign key
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserObject userId;

	// this is a foreign key
	@Column(name = "status")
	private String status;

	// getter and setter>

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserObject getUserId() {
		return userId;
	}

	public void setUserId(UserObject userId) {
		this.userId = userId;
	}

	/*
	 * public List<CartItemObject> getCartItems() { return cartItems; }
	 * 
	 * public void setCartItems(List<CartItemObject> cartItems) { this.cartItems =
	 * cartItems; }
	 */

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}