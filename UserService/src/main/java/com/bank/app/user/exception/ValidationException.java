package com.bank.app.user.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationException extends javax.validation.ValidationException {
	private static final long serialVersionUID = 1L;
	private final List<String> messageList;
}
