package it.objectmethod.ecommerce.spring.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.spring.dto.CartItemDto;
import it.objectmethod.ecommerce.spring.models.CartItemObject;

@Component
public class CartItemMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CartItemObject toEntity(CartItemDto dto) {
		return modelMapper.map(dto, CartItemObject.class);
	}

	public CartItemDto toDTO(CartItemObject cartItem) {
		return modelMapper.map(cartItem, CartItemDto.class);
	}

}
