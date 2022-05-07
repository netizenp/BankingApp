package com.bank.app.account.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final List<String> exceptionMsg;
}
