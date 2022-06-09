package com.bank.app.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.account.dao.AccountRepo;
import com.bank.app.account.feign.UserFeign;
import com.bank.app.account.modal.Account;
import com.bank.app.account.modal.User;
import com.bank.app.account.utility.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private UserFeign userFeign;

	/**
	 * add user object into database return 201 status code
	 */
	@Override
	public ResponseEntity<ResponseData> addAccount(Account account, int userId) {
		log.info("service: add account with provided account object");
		ResponseEntity<User> userResponseEntity = userFeign.getUser(userId);
		log.info("user proxy me hi dikat aa gayi");
		if(userResponseEntity.hasBody()) {
			account.setUserId(userResponseEntity.getBody().getUserId());
			accountRepo.save(account);
			log.info("provided account object is saved");
			List<String> message = new ArrayList<>();
			message.add("Account created successfully");
			ResponseData responseData = new ResponseData(account, "true", message);
			log.info("return the response with true success");
			return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
		}
		log.error("no account object is found with given account id");
		throw new EntityNotFoundException("User with user ID: " + userId + " not exist");
	}

	/**
	 * update the entity associated with provide account id return 200 status code
	 * for success
	 */
	@Override
	public ResponseEntity<ResponseData> updateAccount(Account account) {
		log.info("service: update account object with given account id");
		if (accountRepo.existsById(account.getAccountId())) {
			log.info("account object with given account id is present");
			accountRepo.save(account);
			log.info("account object  with given account id is updated");
			List<String> message = new ArrayList<>();
			message.add("Successfully updated the entity with ID: " + account.getAccountId());
			ResponseData responseData = new ResponseData(account, "true", message);
			log.info("return the response object with true success");
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		}
		log.error("no account object is found with given account id");
		throw new EntityNotFoundException("Account with account Number: " + account.getAccountId() + " not exist");
	}

	/**
	 * delete the entity associated with provided account id return 200 status code
	 * for success
	 */
	@Override
	public ResponseEntity<ResponseData> deleteAccount(Long accountId) {
		log.info("service: delete account object with given account id");
		Optional<Account> account = accountRepo.findById((long) accountId);
		if (account.isPresent()) {
			log.info("account object with given account id is present");
			accountRepo.deleteById(accountId);			
			log.info("account object with given account id is deleted");
			List<String> message = new ArrayList<>();
			message.add("Successfully deleted the filed with ID: " + accountId);
			ResponseData responseData = new ResponseData(account.get(), "true", message);
			log.info("return the response object with true success");
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		}
		log.error("account object with given account id is not present");
		throw new EntityNotFoundException();
	}

	/**
	 * return the account object if present associated with provided account id
	 */
	@Override
	public ResponseEntity<Account> getAccount(Long accountId) {
		log.info("service: get account object associated with given account id");
		Optional<Account> user = accountRepo.findById(accountId);
		if (user.isPresent()) {
			log.info("return account object associated with given account id");
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		}
		log.error("No details of account found for provided account id");
		throw new EntityNotFoundException();
	}

	/**
	 * return the list of all the account objects present in database
	 */
	@Override
	public ResponseEntity<List<Account>> getAllAccount() {
		log.info("service: get all available accounts");
		List<Account> account = accountRepo.findAll();
		if (!account.isEmpty()) {
			log.info("return list of all available account objects");
			return ResponseEntity.ok().body(account);
		}
		log.error("no account object is found in database");
		throw new EntityNotFoundException();
	}

	/**
	 * return the list of objects associated with provided user id
	 */
	@Override
	public ResponseEntity<List<Account>> getAccountbyUserId(int userId) {
		List<Account> accountDto = accountRepo.findByUserId(userId);
		if (!accountDto.isEmpty()) {
			return ResponseEntity.ok().body(accountDto);
		}
		throw new EntityNotFoundException();
	}
}
