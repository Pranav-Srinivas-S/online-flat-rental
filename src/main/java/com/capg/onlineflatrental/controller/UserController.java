package com.capg.onlineflatrental.controller;

import java.util.List;
import java.util.Optional;

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
	
	@RestController
	@RequestMapping("/api/ofr/user")
	public class UserController {

		@Autowired
		IUserService userService;
		
		@PostMapping("/add-user")
		public ResponseEntity<Object> addUser(@RequestBody User user) throws UserNotFoundException 
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
			if(UserServiceImpl.validateUser(user))
			{
				userDTO = userService.addUser(user);
				userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
			}
			else
				throw new UserNotFoundException("Invalid User Details");
			return userResponse;
		
		}
		
		@PutMapping("/update-user")
		public ResponseEntity<Object> updateUser(@RequestBody User user) throws UserNotFoundException
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
				if(UserServiceImpl.validateUserType(user.getUserType()))
				{
					userDTO = userService.updateUser(user);
					userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
				}
				return userResponse;
			}
		
		@PutMapping("/update-password/{newpass}")
		public ResponseEntity<Object> updatePassword(@RequestBody User user, @PathVariable String newpass) throws UserNotFoundException
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
				if(UserServiceImpl.validateUserType(user.getUserType()))
				{
					userDTO = userService.updatePassword(user, newpass);
					userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
				}
				else
					throw new UserNotFoundException("No User available in given ID");
				return userResponse;
		}	
		
		@PatchMapping("/validate-user/{userName}/{password}")
		public ResponseEntity<String> validateUser(@PathVariable String userName, @PathVariable String password) throws UserNotFoundException {
			ResponseEntity<String> userResponse = new ResponseEntity<String>("User Name and Password Does Not Match", HttpStatus.ACCEPTED);
			if( userName==null)
				throw new UserNotFoundException("No user Found");
			else if(!userService.checkUser(userName, password))
				throw new UserNotFoundException("User name and Password Does not match");
			else
				userResponse = new ResponseEntity<String>("User Name and Password Match!", HttpStatus.ACCEPTED);
			return userResponse;
		}
		
		@DeleteMapping("/remove-user/{id}")
		public ResponseEntity<Object> removeUser(@PathVariable int id) throws UserNotFoundException
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
			Optional<UserDTO> optional = Optional.of(userService.removeUser(id));
			if(optional.isPresent())
			{
				userDTO = optional.get();
				userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
			}
			else
				throw new UserNotFoundException("No User available in given ID");
			return userResponse;
		}

		@GetMapping("/view-user/{id}")
		public ResponseEntity<Object> getUserById(@PathVariable int id) throws UserNotFoundException
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
			Optional<UserDTO> optional = Optional.of(userService.viewUser(id));
			if(optional.isPresent())
			{
				userDTO = optional.get();
				userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
			}
			else
				throw new UserNotFoundException("No User available in given ID");
			return userResponse;
		}
		
		@GetMapping("/view-all-user")
		public List<UserDTO> getAllUsers()
		{
			return userService.viewAllUser();
		}	
		
}