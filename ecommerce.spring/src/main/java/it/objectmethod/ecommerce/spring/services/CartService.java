package it.objectmethod.ecommerce.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.spring.models.CartObject;
import it.objectmethod.ecommerce.spring.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cr;

	public CartObject createCart(CartObject cart) {
		return cr.save(cart);
	}

	public CartObject getCartByUserId(Long userId) {

		return cr.findByUserIdId(userId);
	}

}
