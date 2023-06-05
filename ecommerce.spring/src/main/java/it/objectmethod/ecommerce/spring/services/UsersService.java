package it.objectmethod.ecommerce.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.spring.dto.UserDto;
import it.objectmethod.ecommerce.spring.dto.mapper.UserMapper;
import it.objectmethod.ecommerce.spring.models.CartObject;
import it.objectmethod.ecommerce.spring.models.UserObject;
import it.objectmethod.ecommerce.spring.repository.CartRepository;
import it.objectmethod.ecommerce.spring.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private CartRepository cr;

	@Autowired
	private JwtService jwt;

	@Autowired
	private UserMapper userMapper;

	public UserDto login(UserDto userDto) {
		String username = userDto.getUsername();
		String password = userDto.getPassword();
		UserObject authenticatedUser = ur.login(username, password);

		if (authenticatedUser != null) {
			String token = jwt.createJWTToken(authenticatedUser);
			UserDto authenticatedUserDto = userMapper.toDTO(authenticatedUser);
			authenticatedUserDto.setToken(token);

			// Retrieve and set the cart ID
			CartObject cart = cr.findByUserIdId(authenticatedUser.getId());
			if (cart != null) {
				authenticatedUserDto.setCartId(cart.getId());
			}

			return authenticatedUserDto;
		} else {
			return null;
		}
	}

	public UserDto register(UserDto userDto) {
		// Checking if username is already exist
		String username = userDto.getUsername();
		UserObject existingUser = ur.register(username);

		if (existingUser != null) {
			return null;
		}

		UserObject userObject = userMapper.toEntity(userDto);
		UserObject registeredUser = ur.save(userObject);
		return userMapper.toDTO(registeredUser);
	}

	/*
	 * public Iterable<UserDto> getAllUsers() { Iterable<UserObject> users =
	 * ur.findAll(); List<UserDto> userDtos = new ArrayList<>(); for (UserObject
	 * userObject : users) { userDtos.add(userMapper.toDTO(userObject)); } return
	 * userDtos; }
	 * 
	 * public UserDto getOneUser(Long id) { UserObject userObject =
	 * ur.findById(id).orElse(null); if (userObject != null) { return
	 * userMapper.toDTO(userObject); } return null; }
	 * 
	 * public boolean removeUser(Long id) { if (ur.existsById(id)) {
	 * ur.deleteById(id); return true; } return false; }
	 * 
	 * public void clearList() { ur.deleteAll(); }
	 * 
	 * public boolean editUser(UserDto userDto) { Long id = userDto.getId(); if
	 * (ur.existsById(id)) { UserObject userObject = userMapper.toEntity(userDto);
	 * ur.save(userObject); return true; } return false; }
	 */

}
