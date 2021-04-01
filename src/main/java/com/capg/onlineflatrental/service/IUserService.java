package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.model.UserDTO;

public interface IUserService {

	public UserDTO viewUser(int id) throws UserNotFoundException;
	public List<UserDTO> viewAllUser();
	public UserDTO validateUser(String username,String password) throws UserNotFoundException;
	public UserDTO addUser(User user);
	public UserDTO updateUser(User user);
	public UserDTO updatePassword(User user,String newpass);
	public UserDTO removeUser(int id);
	
}
