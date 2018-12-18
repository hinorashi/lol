package com.framgia.hino.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.framgia.hino.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class HinoExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handler for NotFoundException
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class) // FIXME can we abandon value?
	public ResponseEntity<String> handleNotFound(NotFoundException ex) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.toString(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handler for any other Exceptions
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOthers(Exception ex) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.toString(), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
