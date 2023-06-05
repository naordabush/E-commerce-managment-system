package it.objectmethod.ecommerce.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.spring.dto.CartItemDto;
import it.objectmethod.ecommerce.spring.services.CartItemService;

@CrossOrigin
@RestController
public class CartItemController {

	@Autowired
	private CartItemService cis;

	@GetMapping("/cart-items/get-cart-items/{cartId}")
	public List<CartItemDto> getAllCartItemsByCartId(@PathVariable Long cartId) {
		return cis.getAllCartItemsByCartId(cartId);
	}

	@PostMapping("/cart-items/create")
	public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItem) {
		cis.saveOrUpdateCartItem(cartItem);
		return ResponseEntity.ok(cartItem);
	}

	@DeleteMapping("/cart-items/remove/{id}")
	public ResponseEntity<Void> removeItem(@PathVariable Long id) {
		boolean removed = cis.removeItem(id);
		if (removed) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

}
