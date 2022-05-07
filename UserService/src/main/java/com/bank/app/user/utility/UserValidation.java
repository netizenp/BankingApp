package com.bank.app.user.utility;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.app.user.exception.ValidationException;
import com.bank.app.user.modal.User;

@Component
public class UserValidation {

	@Autowired
	private Validator validator;

	/**
	 * @param user take user object as parameter and validate the same
	 */
	public void userValidate(User user) {
		List<String> exceptionList = new ArrayList<>();
		Set<ConstraintViolation<User>> validate = validator.validate(user);
		if (!validate.isEmpty()) {
			for (ConstraintViolation<User> val : validate) {
				Path propertyPath = val.getPropertyPath();
				Iterator<Node> iterator = propertyPath.iterator();
				while (iterator.hasNext()) {
					Path.Node pathNode = iterator.next();
					exceptionList.add(pathNode.getName() + ": " + val.getMessage());
				}
			}
			throw new ValidationException(exceptionList);
		}
	}
}
