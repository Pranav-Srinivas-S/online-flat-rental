package com.capg.onlineflatrental.controller;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	import com.capg.onlineflatrental.entities.User;
	import com.capg.onlineflatrental.exception.UserNotFoundException;
	import com.capg.onlineflatrental.model.UserDTO;
	import com.capg.onlineflatrental.service.UserServiceImpl;
	
	@RestController
	@RequestMapping("/api/user")
	public class UserController {

		@Autowired
		UserServiceImpl userService;
		
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
				userResponse = new ResponseEntity<Object>("User Insertion Failed", HttpStatus.BAD_REQUEST);
			return userResponse;
		
		}
		
		@PutMapping("/update-user")
		public ResponseEntity<Object> updateUser(@RequestBody User user)
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
			try {
				if(UserServiceImpl.validateUser(user))
				{
					userDTO = userService.updateUser(user);
					userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
				}
				else
					userResponse = new ResponseEntity<Object>("User Updation Failed", HttpStatus.BAD_REQUEST);
			} catch (UserNotFoundException e) {
				
				e.printStackTrace();
			}
			return userResponse;
		}
		@PutMapping("/update-password/{newpass}")
		public ResponseEntity<Object> updatePassword(@RequestBody User user, @PathVariable String newpass)
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
			try {
				if(UserServiceImpl.validateUser(user))
				{
					userDTO = userService.updatePassword(user, newpass);
					userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
				}
				else
					userResponse = new ResponseEntity<Object>("User Updation Failed", HttpStatus.BAD_REQUEST);
			} catch (UserNotFoundException e) {
				
				e.printStackTrace();
			}
			return userResponse;
		}
		
		@DeleteMapping("/delete-user/{id}")
		public ResponseEntity<Object> deleteUser(@PathVariable int id)
		{
			UserDTO tenantDTO = null;
			ResponseEntity<Object> userResponse = null;
			try {
				if(userService.validateUserId(id))
				{
					tenantDTO = userService.removeUser(id);
					userResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
				}
				else
					userResponse = new ResponseEntity<Object>("User Deletion Failed", HttpStatus.BAD_REQUEST);
			} catch (UserNotFoundException e) {
				
				e.printStackTrace();
			}
			return userResponse;
		}
		@GetMapping("/view-user/{id}")
		public ResponseEntity<Object> getUserById(@PathVariable int id)
		{
			UserDTO userDTO = null;
			ResponseEntity<Object> userResponse = null;
			try {
				if(userService.validateUserId(id))
				{
					userDTO = userService.viewUser(id);
					userResponse = new ResponseEntity<Object>(userDTO, HttpStatus.ACCEPTED);
				}
				else
					userResponse = new ResponseEntity<Object>("No User available in given ID", HttpStatus.BAD_REQUEST);
			} catch (UserNotFoundException e) {
			
				e.printStackTrace();
			}
			return userResponse;
		}
		
		@GetMapping("/view-all-user")
		public List<UserDTO> getAllUsers()
		{
			return userService.viewAllUser();
		}
		
		@ExceptionHandler({UserNotFoundException.class})
		public ResponseEntity<String> handleException()
		{
			return new ResponseEntity<String>("User Not Found ", HttpStatus.NOT_FOUND);
		}
		
	}
