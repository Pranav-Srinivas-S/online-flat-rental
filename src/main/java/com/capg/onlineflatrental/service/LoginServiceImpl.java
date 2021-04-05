package com.capg.onlineflatrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.capg.onlineflatrental.entities.Login;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.repository.ILoginRepository;
public class LoginServiceImpl {
	
	@Autowired
	public ILoginRepository loginRepo;
	
	
	public boolean validateUserId(int id) throws LoginNotFoundException
	{
		boolean flag = loginRepo.existsById(id);
		if(flag == false)
			throw new LoginNotFoundException("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId");
		return flag;
	}
	
	
	
	public static boolean validatePassword(String password)
    {
		 if (!((password.length() >= 8)
	              && (password.length() <= 15))) {
	            return false;
	        }
		 if (password.contains(" ")) {
	            return false;
	        }
	        if (true) {
	            int count = 0;
	            for (int i = 0; i <= 9; i++) {
	                String str1 = Integer.toString(i);
	                if (password.contains(str1)) {
	                    count = 1;
	                }
	            }
	            if (count == 0) {
	                return false;
	            }
	            if (!(password.contains("@") || password.contains("#")
	                    || password.contains("!") || password.contains("~")
	                    || password.contains("$") || password.contains("%")
	                    || password.contains("^") || password.contains("&")
	                    || password.contains("*") || password.contains("(")
	                    || password.contains(")") || password.contains("-")
	                    || password.contains("+") || password.contains("/")
	                    || password.contains(":") || password.contains(".")
	                    || password.contains(", ") || password.contains("<")
	                    || password.contains(">") || password.contains("?")
	                    || password.contains("|"))) {
	                  return false;
	              }
	            if (true) {
	                int count1 = 0;
	      
	                for (int i = 65; i <= 90; i++) {
	      
	                    char c = (char)i;
	      
	                    String str1 = Character.toString(c);
	                    if (password.contains(str1)) {
	                        count1 = 1;
	                    }
	                }
	                if (count1 == 0) {
	                    return false;
	                }
	            }
	            if (true) {
	                int count1 = 0;
	      
	                for (int i = 90; i <= 122; i++) {
	                    char c = (char)i;
	                    String str1 = Character.toString(c);
	      
	                    if (password.contains(str1)) {
	                        count1 = 1;
	                    }
	                }
	                if (count1 == 0) {
	                    return false;
	                }
	            }
	        }
		 return true;
    }
	public static boolean validateLogin(Login login) throws UserNotFoundException {
		boolean flag = false;
		if(login == null)
			throw new UserNotFoundException("User details cannot be blank");
		else if(!validatePassword(login.getLoginPassword()))
			throw new UserNotFoundException("Format for Password is Wrong\r\n"
					+ "\r\n"
					+"Please Enter Password Again :\r\n"
					+ "____________________________________________________________\r\n"
					+ "\r\n"
					+"Valid Format for Password :\r\n"
					+ "\r\n"
					+ "Password should not contain any space.\r\n"
					+ "Password should contain at least one digit(0-9).\r\n"
					+ "Password length should be between 8 to 15 characters.\r\n"
					+ "Password should contain at least one lowercase letter(a-z).\r\n"
					+ "Password should contain at least one uppercase letter(A-Z).\r\n"
					+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)");
		else
			flag = true;
		return flag;
	}



	public boolean validateLogin(int id, String password) throws LoginNotFoundException {
		boolean flag = false;
		Login login = loginRepo.findByUserName(id);
		if(login == null)
			throw new LoginNotFoundException("Invalid User Name");
		if (login.getLoginPassword().equals(password) && login != null)
			flag = true;
		else 
			throw new LoginNotFoundException("Password does not Match");
		return flag;
	}

}