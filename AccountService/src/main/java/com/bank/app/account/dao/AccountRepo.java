package com.bank.app.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.account.modal.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
