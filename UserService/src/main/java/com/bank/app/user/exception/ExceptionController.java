package com.bank.app.user.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.app.user.utility.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionController extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 * @return handle the exceptions which raise due to invalid User Entity Objects
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseData> handleUserValidationException(ValidationException e) {
		log.error("exception in validations");
		List<String> validationExceptionList = new ArrayList<>();
		for (String s : e.getMessageList()) {
			validationExceptionList.add(s);
		}
		ResponseData responseData = new ResponseData(null, "false", validationExceptionList);
		log.error("returning response body with false success");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
	}

	/**
	 * @param e
	 * @return handle the exception which are raise when the requested entity is not
	 *         present
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseData> handleNotFoundException(EntityNotFoundException e) {
		log.error("exception: not found, returning response body with false success");
		List<String> validationExceptionList = new ArrayList<>();
		validationExceptionList.add(e.getMessage());
		ResponseData responseData = new ResponseData(null, "false", validationExceptionList);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
	}

	/**
	 * @param e
	 * @return handle the general exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseData> handleGeneralException(Exception e) {
		log.error("handling generalized exception");
		e.fillInStackTrace();
		List<String> generalExceptionList = new ArrayList<>();
		generalExceptionList.add(e.getLocalizedMessage());
		ResponseData responseData = new ResponseData(null, "false", generalExceptionList);
		log.error("returing response body with false success 500 error code");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
	}

}
