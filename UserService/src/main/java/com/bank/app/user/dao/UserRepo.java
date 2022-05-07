package com.bank.app.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.user.modal.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
