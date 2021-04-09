package com.capg.onlineflatrental.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.service.ILoginService;

/*
 * Author 1 : PRNANAV SRINIVAS S
 * Author 2 : RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
 * Version : 1.0
 * Date : 06-04-2021
 * Description : This is Login Controller
*/

@RestController
@RequestMapping("/api/ofr/login")
public class LoginController {
	
	static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	public static final String ID = null;

	@Autowired
	private ILoginService loginService;
	
	/************************************************************************************
	 * Method: validateLogin
	 * Description: It is used to validate login details.
	 * @param user: int login id,String User name, String password
	 * @returns user It returns login with details
	 * @PatchMapping: It is used to handle the HTTP PATCH requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
	 * 			   PRNANAV SRINIVAS S
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@PatchMapping("/validate-login")
	public ResponseEntity<String> validateLogin(@RequestBody User user)
			throws LoginNotFoundException, UserNotFoundException {
		LOGGER.info("validate-login URL is opened");
		LOGGER.info("validateLogin() is initiated");
		ResponseEntity<String> loginResponse;
		if (!loginService.login(user))
			throw new LoginNotFoundException("Login id  and Password Does not match");
		else
			loginResponse = new ResponseEntity<>("Login Successful!", HttpStatus.ACCEPTED);
		LOGGER.info("validateLogin() has executed");
		return loginResponse;
	}

}
