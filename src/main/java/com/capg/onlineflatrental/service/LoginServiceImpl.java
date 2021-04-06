package com.capg.onlineflatrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.repository.ILoginRepository;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	ILoginRepository loginRepo;
	
	@Autowired
	UserServiceImpl userService;

	@Override
	public boolean Login(User user) throws LoginNotFoundException, UserNotFoundException {
		boolean flag = false;
		if(user == null)
			throw new LoginNotFoundException("User Details cannot be Empty");
		else if(!userService.checkUser(user.getUserId(), user.getUserName(), user.getPassword()))
			throw new LoginNotFoundException("Invalid UserName or Password");
		else
			flag = true;
		return flag;
	}

}