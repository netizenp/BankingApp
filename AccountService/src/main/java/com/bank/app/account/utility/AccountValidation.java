package com.bank.app.account.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.app.account.exception.ValidationException;
import com.bank.app.account.modal.Account;

@Component
public class AccountValidation {
	@Autowired
	private Validator validator;

	/**
	 * @param account take account object as parameter and validate the same
	 */
	public void accountValidate(Account account) {
		List<String> exceptionList = new ArrayList<>();
		Set<ConstraintViolation<Account>> validate = validator.validate(account);
		if (!validate.isEmpty()) {
			for (ConstraintViolation<Account> val : validate) {
				exceptionList.add(val.getMessage());
			}
			throw new ValidationException(exceptionList);
		}
	}
}
