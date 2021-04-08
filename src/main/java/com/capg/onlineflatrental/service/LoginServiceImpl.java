package com.capg.onlineflatrental.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;

/*
 * Author : RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
 * Version : 1.0
 * Date : 03-04-2021
 * Description : It is a login service class that provides the services to validate login details.
*/

@Service
public class LoginServiceImpl implements ILoginService {
	
	static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	UserServiceImpl userService;
	
	/*
	 * Method:                          	Login.
     *Description:                      	To check the user name and password is matching or not.
         *@param id:                  		id for validating login.
	     *@param username:                  user name for validating login.
	     *@param password:                  password for validating login.
		 *@returns User                  - 	it will check the user details matching successfully or not.
		 *@throws UserNotFoundException  -  It is raised due to mismatch of user details. 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */

	@Override
	public boolean login(User user) throws LoginNotFoundException, UserNotFoundException {
		LOGGER.info("Login() service is initiated");
		boolean flag = false;
		if(user == null)
			throw new LoginNotFoundException("User Details cannot be Empty");
		else if(!userService.checkUser(user.getUserId(), user.getUserName(), user.getPassword()))
			throw new LoginNotFoundException("Invalid UserName or Password");
		else
			flag = true;
		LOGGER.info("Login() service is executed");
		return flag;
	}

}