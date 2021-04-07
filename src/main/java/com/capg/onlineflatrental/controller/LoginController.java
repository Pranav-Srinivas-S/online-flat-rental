package com.capg.onlineflatrental.controller;

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

@RestController
@RequestMapping("/api/ofr/login")
public class LoginController {
	
	public static final String id = null;

		@Autowired
		ILoginService loginService;
		
		@PatchMapping("/validate-login")
		public ResponseEntity<String> validateLogin(@RequestBody User user) throws LoginNotFoundException, UserNotFoundException
		{
			ResponseEntity<String> loginResponse = new ResponseEntity<String>("User Id and Password Does Not Match", HttpStatus.ACCEPTED);
			if(!loginService.Login(user))
				throw new LoginNotFoundException("Login id  and Password Does not match");
			else
				loginResponse = new ResponseEntity<String>("Login Successful!", HttpStatus.ACCEPTED);
			return loginResponse;
		}
}
