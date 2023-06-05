package it.objectmethod.ecommerce.spring.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.objectmethod.ecommerce.spring.dto.CartItemDto;
import it.objectmethod.ecommerce.spring.dto.mapper.CartItemMapper;
import it.objectmethod.ecommerce.spring.models.CartItemObject;
import it.objectmethod.ecommerce.spring.repository.CartItemRepository;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cir;

	@Autowired
	private CartItemMapper cartItemMapper;

	public List<CartItemDto> getAllCartItemsByCartId(Long cartId) {
		List<CartItemObject> cartItems = cir.findAllByCartId(cartId);
		List<CartItemDto> cartItemDtos = new ArrayList<>();

		for (CartItemObject cartItem : cartItems) {
			CartItemDto cartItemDto = cartItemMapper.toDTO(cartItem);
			cartItemDtos.add(cartItemDto);
		}

		return cartItemDtos;
	}

	@Transactional
	public CartItemDto saveOrUpdateCartItem(CartItemDto cartItem) {
		Long cartId = cartItem.getCart().getId();
		Long itemId = cartItem.getItem().getId();

		CartItemObject existingOrder = cir.findByCartIdAndItemId(cartId, itemId);

		if (existingOrder != null) {
			existingOrder.setQuantity(cartItem.getQuantity());
		} else {
			existingOrder = cartItemMapper.toEntity(cartItem);
		}

		existingOrder = cir.save(existingOrder);
		return cartItemMapper.toDTO(existingOrder);
	}

	public boolean removeItem(Long id) {
		if (cir.existsById(id)) {
			cir.deleteById(id);
			return true;
		}
		return false;
	}

	public List<CartItemObject> getCartItemsByDate(LocalDate date) {
		// Convert LocalDate to Date
		Date startDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(date.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

		return cir.findByOrderTimeBetween(startDate, endDate);
	}

}
