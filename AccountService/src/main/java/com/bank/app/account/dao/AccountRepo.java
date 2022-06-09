package com.bank.app.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.account.modal.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long>{

    @Query(value = "SELECT * FROM account WHERE account.userId = :userId", nativeQuery = true)
    List<Account> findByUserId(@Param("userId") int userId);
}
