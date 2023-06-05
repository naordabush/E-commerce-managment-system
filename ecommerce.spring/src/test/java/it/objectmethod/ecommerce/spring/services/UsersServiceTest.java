package it.objectmethod.ecommerce.spring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.objectmethod.ecommerce.spring.dto.UserDto;
import it.objectmethod.ecommerce.spring.dto.mapper.UserMapper;
import it.objectmethod.ecommerce.spring.models.CartObject;
import it.objectmethod.ecommerce.spring.models.UserObject;
import it.objectmethod.ecommerce.spring.repository.CartRepository;
import it.objectmethod.ecommerce.spring.repository.UserRepository;

class UsersServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private CartRepository cartRepository;

	@Mock
	private JwtService jwtService;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UsersService usersService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void login_success() {
		// Arrange
		UserDto inputUserDto = new UserDto();
		inputUserDto.setUsername("test");
		inputUserDto.setPassword("pass");

		UserObject userObject = new UserObject();
		userObject.setId(1L);
		userObject.setUsername("test");
		userObject.setPassword("pass");

		// Mockito
		when(userRepository.login("test", "pass")).thenReturn(userObject);
		when(userMapper.toDTO(userObject)).thenReturn(inputUserDto);
		when(jwtService.createJWTToken(userObject)).thenReturn("valid-token");

		CartObject cartObject = new CartObject();
		cartObject.setId(1L);
		cartObject.setUserId(userObject);

		when(cartRepository.findByUserIdId(1L)).thenReturn(cartObject);

		// Act
		UserDto result = usersService.login(inputUserDto);

		// Assert
		assertNotNull(result);
		assertEquals("valid-token", result.getToken());
		assertEquals(1L, result.getCartId());
	}

	@Test
	void login_failure() {
		// Arrange
		UserDto inputUserDto = new UserDto();
		inputUserDto.setUsername("invalid");
		inputUserDto.setPassword("wrongpass");

		// Mockito
		when(userRepository.login("invalid", "wrongpass")).thenReturn(null);

		// Act
		UserDto result = usersService.login(inputUserDto);

		// Assert
		assertNull(result);

	}

	@Test
	void register_success() {
		// Arrange
		UserDto inputUserDto = new UserDto();
		inputUserDto.setUsername("newuser");
		inputUserDto.setPassword("newpass");

		UserObject registeredUser = new UserObject();
		registeredUser.setId(1L);
		registeredUser.setUsername("newuser");
		registeredUser.setPassword("newpass");

		// Mockito
		when(userRepository.register("newuser")).thenReturn(null);
		when(userMapper.toEntity(inputUserDto)).thenReturn(registeredUser);
		when(userMapper.toDTO(registeredUser)).thenReturn(inputUserDto);
		when(userRepository.save(registeredUser)).thenReturn(registeredUser);

		// Act
		UserDto result = usersService.register(inputUserDto);

		// Assert
		assertNotNull(result);
		assertEquals("newuser", result.getUsername());

	}

	@Test
	void register_failure() {
		// Arrange
		UserDto inputUserDto = new UserDto();
		inputUserDto.setUsername("existinguser");
		inputUserDto.setPassword("newpass");

		UserObject existingUser = new UserObject();
		existingUser.setId(1L);
		existingUser.setUsername("existinguser");
		existingUser.setPassword("existingpass");

		// Mockito
		when(userRepository.register("existinguser")).thenReturn(existingUser);

		// Act
		UserDto result = usersService.register(inputUserDto);

		// Assert
		assertNull(result);

	}

}
