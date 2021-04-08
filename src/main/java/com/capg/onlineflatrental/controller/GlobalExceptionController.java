package com.capg.onlineflatrental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capg.onlineflatrental.exception.CommonException;

/*
 * Author : PRANAV SRINIVAS S
 * Version : 1.0
 * Date : 04-04-2021
 * Description : This is Global Exception Controller
*/

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<String> exceptionHandler(Exception exception)
	{
		String errorMessage = exception.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
}
