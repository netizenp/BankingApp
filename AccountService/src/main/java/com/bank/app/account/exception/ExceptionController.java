package com.bank.app.account.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.app.account.utility.ResponseData;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pankajve
 *
 */
@Slf4j
@ControllerAdvice
public class ExceptionController {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 * @return handle the exceptions which raise due to invalid account entity
	 *         objects
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseData> handleUserValidationException(ValidationException e) {
		log.error("exception in validations");
		List<String> exceptionMessage = new ArrayList<>();
		for (String s : e.getExceptionMsg()) {
			exceptionMessage.add(s);
		}
		ResponseData responseData = new ResponseData(null, "false", exceptionMessage);
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
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * @param e
	 * @return handle the general exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseData> handleGeneralException(Exception e) {
		log.error("generalized exception");
		e.fillInStackTrace();
		List<String> exceptionMessage = new ArrayList<>();
		exceptionMessage.add(e.getLocalizedMessage());
		ResponseData responseData = new ResponseData(null, "false", exceptionMessage);
		log.error("returing response body with false success");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
	}

}
