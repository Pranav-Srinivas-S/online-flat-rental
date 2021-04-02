package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.model.UserDTO;

public class UserServiceImpl implements IUserService{

	@Override
	public UserDTO viewUser(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> viewAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO validateUser(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updatePassword(User user, String newpass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO removeUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
