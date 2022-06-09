package com.bank.app.account.utility;

import java.util.List;

import com.bank.app.account.modal.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseData {
	private Account data;
	private String success;
	private List<String> message;
}
