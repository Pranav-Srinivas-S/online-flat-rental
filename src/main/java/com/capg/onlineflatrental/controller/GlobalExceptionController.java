package com.capg.onlineflatrental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception exception)
	{
		String errorMessage = exception.getMessage();
		return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(value=ConstraintViolationException.class)
//	public ResponseEntity<Object> exceptionConstraintViolationException(ConstraintViolationException exception)
//	{
//		StringBuilder msg = new StringBuilder();
//		exception.getConstraintViolations().forEach(i->msg.append(i.getConstraintDescriptor().getMessageTemplate()));
//		return new ResponseEntity<>(msg.toString(),HttpStatus.NOT_ACCEPTABLE);
//	}
	
}
