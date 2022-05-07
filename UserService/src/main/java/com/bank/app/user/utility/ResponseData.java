package com.bank.app.user.utility;

import java.util.List;

import com.bank.app.user.modal.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseData {
	private User data;
	private String success;
	private List<String> message;
}
