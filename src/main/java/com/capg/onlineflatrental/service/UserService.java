package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;

public class UserService implements IUserService{

	@Override
	public User viewUser(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> viewAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User validateUser(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updatePassword(User user, String newpass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User removeUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
