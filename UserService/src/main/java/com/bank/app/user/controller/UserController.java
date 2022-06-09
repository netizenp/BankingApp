package com.bank.app.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.user.modal.User;
import com.bank.app.user.service.UserService;
import com.bank.app.user.utility.ResponseData;
import com.bank.app.user.utility.UserValidation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

	/**
	 * @param user
	 * @return This method receives the User Object in order to add the User Entity
	 */
	@PostMapping("/adduser")
	public ResponseEntity<ResponseData> addUser(@RequestBody User user) {
		log.info("hit api for add user");
		System.out.println(user);
		userValidation.userValidate(user);
		log.info("Anotation based validation for user object is done");
		return userService.addUser(user);
	}

	/**
	 * @param user
	 * @return This Method takes the User Object in order to update the associated
	 *         User Entity
	 */
	@PutMapping("/updateuser")
	public ResponseEntity<ResponseData> updateUser(@RequestBody User user) {
		log.info("hit api for update user");
		userValidation.userValidate(user);
		log.info("Anotation based validation for user object is done");
		return userService.updateUser(user);
	}

	/**
	 * @param userId
	 * @return This Method takes the user id of User Object in order to delete the
	 *         the entity
	 */
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<ResponseData> deleteUser(@PathVariable int userId) {
		log.info("hit api for delete user");
		System.out.println(userId);
		return userService.deleteUser(userId);
	}

	/**
	 * @param userId
	 * @return This Method takes the user id and return the user object associated
	 *         with provided user id
	 */
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId) {
		log.info("hit api in order to fetch user for particular user id");
		return userService.getUser(userId);
	}

	/**
	 * @return This method fetch all the user objects present and return the list
	 */
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser() {
		log.info("hit api in order to fetch all available user");
		return userService.getAllUser();
	}
}
