package it.objectmethod.ecommerce.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.spring.dto.UserDto;
import it.objectmethod.ecommerce.spring.services.UsersService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UsersService us;

	@PostMapping("/users/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
		UserDto authenticatedUser = us.login(userDto);
		if (authenticatedUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			// cs.getCartByUserId(userDto);
			return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);

		}
	}

	@PostMapping("/users/register")
	public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
		UserDto registeredUser = us.register(userDto);
		if (registeredUser == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(registeredUser, HttpStatus.OK);
		}
	}

	/*
	 * @GetMapping(value = "/users/all", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Iterable<UserDto> getAllUsers() {
	 * return us.getAllUsers(); }
	 * 
	 * @GetMapping("/users/get/{id}") public ResponseEntity<UserDto>
	 * getOneUser(@PathVariable Long id) { UserDto userDto = us.getOneUser(id); if
	 * (userDto != null) { return new ResponseEntity<>(userDto, HttpStatus.OK); }
	 * else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 * 
	 * @PutMapping("users/edit") public ResponseEntity<Void> editUser(@RequestBody
	 * UserDto userDto) { boolean edited = us.editUser(userDto); if (edited) {
	 * return new ResponseEntity<>(HttpStatus.NO_CONTENT); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 * 
	 * @DeleteMapping("/users/remove/{id}") public ResponseEntity<Void>
	 * removeUser(@PathVariable Long id) { boolean removed = us.removeUser(id); if
	 * (removed) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); } else {
	 * return new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 * 
	 * @PostMapping("/users/clear") public ResponseEntity<Void> clearList() {
	 * us.clearList(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 */
}
