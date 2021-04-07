package com.capg.onlineflatrental.service;

import java.util.List;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.model.UserDTO;

public interface IUserService {

	public  UserDTO viewUser(int id) throws UserNotFoundException;
	public List<UserDTO> viewAllUser();
	public UserDTO addUser(User user) throws UserNotFoundException;
	public UserDTO updateUser(User user)  throws UserNotFoundException;
	public UserDTO updatePassword(User user,String newpass) throws UserNotFoundException;
	public UserDTO removeUser(int id) throws UserNotFoundException;
	public boolean checkUser(String userName, String password) throws UserNotFoundException;
	
	//
}
