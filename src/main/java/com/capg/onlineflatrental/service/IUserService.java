package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;

public interface IUserService {

	public User viewUser(int id) throws UserNotFoundException;
	public List<User> viewAllUser();
	public User validateUser(String username,String password) throws UserNotFoundException;
	public User addUser(User user);
	public User updateUser(User user);
	public User updatePassword(User user,String newpass);
	public User removeUser(int id);
	
}
