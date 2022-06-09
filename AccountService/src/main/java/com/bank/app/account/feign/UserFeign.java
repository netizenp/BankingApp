package com.bank.app.account.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.app.account.modal.User;

@FeignClient(name = "userservice"/*, url = "localhost:9001/user"*/)
public interface UserFeign {
	
	/**
	 * @param userId
	 * @return call the getUser() function of user service and receives the user
	 *         object with provided user id
	 */
	@GetMapping("/user/getuser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId);

}
