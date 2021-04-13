package com.capg.onlineflatrental.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.model.UserDTO;
import com.capg.onlineflatrental.service.IUserService;
import com.capg.onlineflatrental.service.UserServiceImpl;

/*************************************************************************************
 * Author : RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
 * Version : 1.0
 * Date : 04-04-2021
 * Description : This is User Controller.
*************************************************************************************/

@RestController
@RequestMapping("/api/ofr/user")
public class UserController {

	static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	/************************************************************************************
	 * Method: addUser 
	 * Description: It is used to add user into users table
	 * @param user: user's reference variable.
	 * @returns user It returns user with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@PostMapping("/add-user")
	public ResponseEntity<Object> addUser(@RequestBody User user) throws UserNotFoundException {
		LOGGER.info("add-user URL is opened");
		LOGGER.info("addUser() is initiated");
		UserDTO userDTO = null;
		ResponseEntity<Object> userResponse = null;
		userDTO = userService.addUser(user);
		userResponse = new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addUser() has executed");
		return userResponse;
	}

	/************************************************************************************
	 * Method: updateUser 
	 * Description: It is used to update user into users table
	 * @param user: user's reference variable.
	 * @returns user It returns user with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@PutMapping("/update-user")
	public ResponseEntity<Object> updateUser(@RequestBody User user) throws UserNotFoundException {
		LOGGER.info("update-user URL is opened");
		LOGGER.info("updateUser() is initiated");
		UserDTO userDTO = null;
		ResponseEntity<Object> userResponse = null;
		userDTO = userService.updateUser(user);
		userResponse = new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateUser() has executed");
		return userResponse;
	}

	/************************************************************************************
	 * Method: updatePassword 
	 * Description: It is used to update password into users table
	 * @param user: user's reference variable, String newpass
	 * @returns user It returns user with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@PutMapping("/update-password/{newpass}")
	public ResponseEntity<Object> updatePassword(@RequestBody User user, @PathVariable String newpass)
			throws UserNotFoundException {
		LOGGER.info("update-password URL is opened");
		LOGGER.info("updatePassword() is initiated");
		UserDTO userDTO = null;
		ResponseEntity<Object> userResponse = null;
		if (UserServiceImpl.validateUserType(user.getUserType())) {
			userDTO = userService.updatePassword(user, newpass);
			userResponse = new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
		} 
		else
			throw new UserNotFoundException("No User available in given ID");
		LOGGER.info("updatePassword() has executed");
		return userResponse;
	}
	
	/************************************************************************************
	 * Method: validateUser 
	 * Description: It is used to validate user in users table
	 * @param user: String userName, String password
	 * @returns user It returns user with details
	 * @PatchMapping: It is used to handle the HTTP PATCH requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@PatchMapping("/validate-user/{userName}/{password}")
	public ResponseEntity<String> validateUser(@PathVariable String userName, @PathVariable String password)
			throws UserNotFoundException {
		LOGGER.info("validate-user URL is opened");
		LOGGER.info("validateUser() is initiated");
		ResponseEntity<String> userResponse;
		if (userName == null)
			throw new UserNotFoundException("No user Found");
		else if (!userService.validateUser(userName, password))
			throw new UserNotFoundException("User name and Password Does not match");
		else
			userResponse = new ResponseEntity<>("User Name and Password Match!", HttpStatus.ACCEPTED);
		LOGGER.info("validateUser() has executed");
		return userResponse;
	}
	
	/************************************************************************************
	 * Method: removeUser 
	 * Description: It is used to remove user from users table
	 * @param user: int id
	 * @returns user It returns user with details
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@DeleteMapping("/remove-user/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable int id) throws UserNotFoundException {
		LOGGER.info("remove-user URL is opened");
		LOGGER.info("removeUser() is initiated");
		UserDTO userDTO = null;
		userDTO = userService.removeUser(id);
		LOGGER.info("removeUser() has executed");
		return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
	}

	/************************************************************************************
	 * Method: getUserById 
	 * Description: It is used to view user by id from users table
	 * @param user: int id
	 * @returns user It returns user with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/view-user/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable int id) throws UserNotFoundException {
		LOGGER.info("view-user URL is opened");
		LOGGER.info("getUserById() is initiated");
		UserDTO userDTO = null;
		ResponseEntity<Object> userResponse = null;
		userDTO = userService.viewUser(id);
		userResponse = new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
		LOGGER.info("getUserById() has executed");
		return userResponse;
	}

	/************************************************************************************
	 * Method: getAllUsers 
	 * Description: It is used to view all users in users table
	 * @returns user It returns user with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/view-all-users")
	public List<UserDTO> getAllUsers() {
		LOGGER.info("view-all-user URL is opened");
		LOGGER.info("getAllUsers() is initiated");
		LOGGER.info("getAllUsers() is initiated");
		return userService.viewAllUser();
	}

}