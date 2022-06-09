package com.bank.app.account.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank.app.account.modal.Account;
import com.bank.app.account.utility.ResponseData;

public interface AccountService {

	public ResponseEntity<ResponseData> addAccount(Account account, int userId);

	public ResponseEntity<ResponseData> updateAccount(Account account);

	public ResponseEntity<ResponseData> deleteAccount(Long accountId);

	public ResponseEntity<Account> getAccount(Long accountId);

	public ResponseEntity<List<Account>> getAllAccount();

	public ResponseEntity<List<Account>> getAccountbyUserId(int userId);
	//user ke pass hoga all account with user id
	//account ke pass hoga user detail of particular account number
}
