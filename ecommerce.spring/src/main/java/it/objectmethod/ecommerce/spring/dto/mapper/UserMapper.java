package it.objectmethod.ecommerce.spring.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.spring.dto.UserDto;
import it.objectmethod.ecommerce.spring.models.UserObject;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserObject toEntity(UserDto dto) {
		return modelMapper.map(dto, UserObject.class);
	}

	public UserDto toDTO(UserObject user) {
		return modelMapper.map(user, UserDto.class);
	}

}

/*
 * public UserDto toDTO(UserObject user) { UserDto dto = new UserDto();
 * dto.setId(user.getId()); dto.setFirstName(user.getFirstName());
 * dto.setLastName(user.getLastName()); dto.setEmail(user.getEmail());
 * dto.setUsername(user.getUsername()); dto.setPassword(user.getPassword()); //
 * System.out.println("user mapper toDTO"); return dto; }
 * 
 * public UserObject toEntity(UserDto dto) { UserObject user = new UserObject();
 * user.setId(dto.getId()); user.setFirstName(dto.getFirstName());
 * user.setLastName(dto.getLastName()); user.setEmail(dto.getEmail());
 * user.setUsername(dto.getUsername()); user.setPassword(dto.getPassword()); //
 * System.out.println("user mapper toEntity"); return user; }
 * 
 */