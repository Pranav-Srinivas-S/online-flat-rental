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
			//throw new LoginNotFoundException("User Details cannot be Empty");
			flag = false;
		else if(!userService.checkUser(user.getUserId(), user.getUserName(), encryptPassword(user.getPassword())))
			//throw new LoginNotFoundException("Invalid UserName or Password");
			flag = false;
		else
			flag = true;
		LOGGER.info("Login() service is executed");
		return flag;
	}

	public static final String ALPHABETLOWER = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_";
	public static final String ALPHABETUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_";
	
	/*
	 * Method:                          	encryptPassword().
     *Description:                      	To encrypt given password.
	     *@param password:                  password to be encrypted.
		 *@returns encryptedPassword     - 	Encrypted password
     *Created By                         -  PRANAV SRINIVAS S
     *Created Date                       -  08-04-2021                           	 
	 */
	
	public static String encryptPassword(String password)
	{
		int shiftKey=3, charPosition, keyVal;
		char replaceVal;
        String encryptedPassword = "";
        for (int i = 0; i < password.length(); i++)
        {
        	if(Character.isUpperCase(password.charAt(i))) {
        		charPosition = ALPHABETUPPER.indexOf(password.charAt(i));
        		keyVal = (shiftKey + charPosition) % 26;
        		replaceVal = ALPHABETUPPER.charAt(keyVal);
        		encryptedPassword += replaceVal;
            }
        	else {
        		charPosition = ALPHABETLOWER.indexOf(password.charAt(i));
                keyVal = (shiftKey + charPosition) % 26;
                replaceVal = ALPHABETLOWER.charAt(keyVal);
                encryptedPassword += replaceVal;
        	}
        	
        }
        return encryptedPassword;
	}
	
	/*
	 * Method:                          	decryptPassword().
     *Description:                      	To decrypt the encrypted password.
	     *@param password:                  encryptedPassword.
		 *@returns encryptedPassword     - 	Decrypted password
     *Created By                         -  PRANAV SRINIVAS S
     *Created Date                       -  08-04-2021                           	 
	 */
	
	public static String decryptPassword(String encryptedPassword)
	{
		int shiftKey=3, charPosition, keyVal;
		char replaceVal;
        String password = "";
        for (int i = 0; i < encryptedPassword.length(); i++)
        {
        	if(Character.isUpperCase(password.charAt(i))) {
        		charPosition = ALPHABETUPPER.indexOf(encryptedPassword.charAt(i));
        		keyVal = (charPosition - shiftKey) % 26;
        		if (keyVal < 0)
        		{
        			keyVal = ALPHABETUPPER.length() + keyVal;
        		}
        		replaceVal = ALPHABETUPPER.charAt(keyVal);
        		password += replaceVal;
        	}
        	else {
        		charPosition = ALPHABETLOWER.indexOf(encryptedPassword.charAt(i));
        		keyVal = (charPosition - shiftKey) % 26;
        		if (keyVal < 0)
        		{
        			keyVal = ALPHABETLOWER.length() + keyVal;
        		}
        		replaceVal = ALPHABETLOWER.charAt(keyVal);
        		password += replaceVal;
        	}
        }
        return password;
	}
	
}