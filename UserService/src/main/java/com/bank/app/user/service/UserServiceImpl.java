package com.bank.app.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.user.dao.UserRepo;
import com.bank.app.user.modal.User;
import com.bank.app.user.utility.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	/**
	 * add the user object into database return the success with 201 status code
	 */
	@Override
	public ResponseEntity<ResponseData> addUser(User user) {
		log.info("service: add user with provided user object");
		userRepo.save(user);
		log.info("provided user object is saved");
		List<String> message = new ArrayList<>();
		message.add("Entity successfully created");
		ResponseData responseData = new ResponseData(user, "true", message);
		log.info("return the response with true success");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
	}

	/**
	 * update the user entity associated with provided user id return 200 status
	 * code for the success
	 */
	@Override
	public ResponseEntity<ResponseData> updateUser(User user) {
		log.info("service: update user object with given user id");
		if (userRepo.existsById(user.getUserId())) {
			log.info("user object with given user id is present");
			userRepo.save(user);
			log.info("user object with given user id is updated");
			List<String> message = new ArrayList<>();
			message.add("Successfully updated the entity with ID: " + user.getUserId());
			ResponseData responseData = new ResponseData(user, "true", message);
			log.info("return the response object with true success");
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		}
		log.error("no user object is found with given user id");
		throw new EntityNotFoundException("User with user Id: " + user.getUserId() + " is not present");
	}

	/**
	 * delete the user entity associated with provided user id return the success
	 * with 200 status code
	 */
	@Override
	public ResponseEntity<ResponseData> deleteUser(int userId) {
		log.info("service: delete user object with given user id");
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			log.info("user object with given user id is present");
			userRepo.deleteById(userId);
			log.info("user object  with given user id is deleted");
			List<String> message = new ArrayList<>();
			message.add("Successfully deleted the filed with ID: " + userId);
			ResponseData responseData = new ResponseData(user.get(), "true", message);
			log.info("return the response object with true success");
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		}
		log.error("user object with given user id is not present");
		throw new EntityNotFoundException("User with user Id: " + userId + " is not present");
	}

	/**
	 * return the user object if present associated with provided user id
	 */
	@Override
	public ResponseEntity<User> getUser(int userId) {
		log.info("service: get user object associated with given user id");
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			log.info("return user object associated with given user id");
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		}
		log.error("No details of user found for provided user id");
		throw new EntityNotFoundException();
	}

	/**
	 * return the list of all the user objects present in database
	 */
	@Override
	public ResponseEntity<List<User>> getAllUser() {
		log.info("service: get all available users");
		List<User> user = userRepo.findAll();
		if (!user.isEmpty()) {
			log.info("return list of all available user objects");
			return ResponseEntity.ok().body(user);
		}
		log.error("No user object is found in database");
		throw new EntityNotFoundException();
	}

}
