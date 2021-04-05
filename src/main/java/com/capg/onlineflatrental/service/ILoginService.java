package com.capg.onlineflatrental.service;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;

public interface ILoginService {
	
	public boolean Login(User user) throws LoginNotFoundException, UserNotFoundException;

}
