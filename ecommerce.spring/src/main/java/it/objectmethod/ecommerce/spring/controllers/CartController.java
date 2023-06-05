package it.objectmethod.ecommerce.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.spring.models.CartObject;
import it.objectmethod.ecommerce.spring.services.CartService;

@RestController

public class CartController {

	@Autowired
	private CartService cs;

	@GetMapping("/cart/get/{userId}")
	public CartObject getCartByUserId(@PathVariable Long userId) {
		return cs.getCartByUserId(userId);
	}

	@PostMapping("/cart/create")
	public CartObject createCart(@RequestBody CartObject cart) {
		return cs.createCart(cart);
	}

}
