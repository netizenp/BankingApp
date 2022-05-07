package com.bank.app.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank.app.user.modal.User;
import com.bank.app.user.utility.ResponseData;

public interface UserService {
	public ResponseEntity<ResponseData> addUser(User user);

	public ResponseEntity<ResponseData> updateUser(User user);

	public ResponseEntity<ResponseData> deleteUser(int userid);

	public ResponseEntity<User> getUser(int userId);

	public ResponseEntity<List<User>> getAllUser();
}
