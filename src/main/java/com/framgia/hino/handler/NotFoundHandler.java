package com.framgia.hino.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.framgia.hino.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class NotFoundHandler {

	// @ExceptionHandler()
	@ExceptionHandler(NotFoundException.class) // FIXME abandon?
	public ResponseEntity<String> handleNotFound(NotFoundException ex) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.toString(), HttpStatus.NOT_FOUND);
	}
}
