package com.capg.onlineflatrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.onlineflatrental.entities.Login;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.repository.ILoginRepository;
import com.capg.onlineflatrental.service.LoginServiceImpl;

public class LoginController {
	
	public static final String id = null;

	@RestController
	@RequestMapping("/api/login")
	public class UserController {

		@Autowired	
		ILoginRepository loginRepo;
		@Autowired
		LoginServiceImpl loginService;
		
		@PatchMapping("/validate-login/{id}/{password}")
		public ResponseEntity<String> validateLogin(@PathVariable int id, @PathVariable String password) throws LoginNotFoundException {
			ResponseEntity<String> loginResponse = new ResponseEntity<String>("User Id and Password Does Not Match", HttpStatus.ACCEPTED);
			Login existUser = loginRepo.findById(id).orElse(null);
			if(existUser == null)
				throw new LoginNotFoundException("No user Found");
			else if(!loginService.validateLogin(id, password))
				throw new LoginNotFoundException("Login id  and Password Does not match");
			else
				loginResponse = new ResponseEntity<String>("User ID and Password Match!", HttpStatus.ACCEPTED);
			return loginResponse;
		}
		
}
}
