package com.bank.app.account.controller;

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

import com.bank.app.account.modal.Account;
import com.bank.app.account.service.AccountService;
import com.bank.app.account.utility.AccountValidation;
import com.bank.app.account.utility.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountValidation accountValidation;

	/**
	 * @param account
	 * @return This method receives the Account Object in order to add the Account
	 *         Entity
	 */
	@PostMapping("/addaccount/{userId}")
	public ResponseEntity<ResponseData> addAccount(@RequestBody Account account, @PathVariable int userId) {
		System.out.println(account);
		log.info("hit api for add account");
		accountValidation.accountValidate(account);
		log.info("Anotation based validation for account object is done");
		return accountService.addAccount(account, userId);
	}

	/**
	 * @param account
	 * @return This Method takes the Account Object in order to update the
	 *         associated Account Entity
	 */
	@PutMapping("/updateaccount/{accountId}")
	public ResponseEntity<ResponseData> updateAccount(@RequestBody Account account) {
		log.info("hit api for update account");
		accountValidation.accountValidate(account);
		log.info("Anotation based validation for account object is done");
		return accountService.updateAccount(account);
	}

	/**
	 * @param accountId
	 * @return This Method takes the account id of Account Object in order to delete
	 *         the the entity
	 */
	@DeleteMapping("/deleteaccount/{accountId}")
	public ResponseEntity<ResponseData> deleteAccount(@PathVariable("accountId") Long accountId) {
		log.info("hit api for delete account");
		return accountService.deleteAccount(accountId);
	}

	/**
	 * @param accountId
	 * @return This Method takes the account id and return the account object
	 *         associated with provided account id
	 */
	@GetMapping("/getaccount/{accountId}")
	public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
		log.info("hit api in order to fetch account for particular user id");
		return accountService.getAccount(accountId);
	}

	/**
	 * @return This method fetch all the account objects present and return the list
	 */
	@GetMapping("/getallaccount")
	public ResponseEntity<List<Account>> getAllAccount() {
		log.info("hit api in order to fetch all available account");
		return accountService.getAllAccount();
	}

	/**
	 * @param userId
	 * @return This method takes the user id and fetch all account associated with
	 *         user id and return the list
	 */
	@GetMapping("/getallaccount/userid/{userId}")
	public ResponseEntity<List<Account>> getAllAccountByUserId(@PathVariable int userId) {
		log.info("hit api in order to fetch all available accounts associated with given user id");
		return accountService.getAccountbyUserId(userId);
	}
}
