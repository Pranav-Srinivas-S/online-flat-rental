package com.capg.onlineflatrental.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.model.UserDTO;
import com.capg.onlineflatrental.repository.IUserRepository;
import com.capg.onlineflatrental.util.UserUtils;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired	
	IUserRepository userRepo;
	
	@Override
	public UserDTO viewUser(int id) throws UserNotFoundException {
		User existUser = userRepo.findById(id).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId");
		return UserUtils.convertToUserDto(existUser);
	}
	
	@Override
	public List<UserDTO> viewAllUser() {
		List<User> userList = userRepo.findAll();
		return UserUtils.convertToUserDtoList(userList);
	}
	
	@Override
	public UserDTO addUser(User user) {
		User userEntity;
		if(user == null)
			userEntity = null;
		else
			userEntity = userRepo.save(user);
		return UserUtils.convertToUserDto(userEntity);
	}
	
	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		User userEntity;
		if(user == null)
			userEntity = null;
		User existUser = userRepo.findById(user.getUserId()).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user found");
		else
			userEntity = userRepo.save(user);
		return UserUtils.convertToUserDto(userEntity);
	}
	
	@Override
	public UserDTO updatePassword(User user, String newpass) throws UserNotFoundException {
		User userEntity;
		if(user == null)
			userEntity = null;
		User existUser = userRepo.findById(user.getUserId()).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user found");
		else {
			user.setPassword(newpass);
		userEntity = userRepo.save(user);}
		return UserUtils.convertToUserDto(userEntity);
	}
	
	@Override
	public UserDTO removeUser(int id) throws UserNotFoundException {
		User existUser = userRepo.findById(id).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId");
		else
			userRepo.delete(existUser);
		return UserUtils.convertToUserDto(existUser);
	}
	
	public boolean validateUser(int id, String userName, String password) throws UserNotFoundException {
		boolean flag = false;
		User user = userRepo.findByIdAndName(id, userName);
		if(!validateUserId(id))
			throw new UserNotFoundException("No User available in given ID");
		else if(user == null)
			throw new UserNotFoundException("Invalid User Name");
		if (user.getPassword().equals(password) && user != null)
			flag = true;
		else 
			throw new UserNotFoundException("Password does not Match");
		return flag;
	}
	
	public boolean validateUserId(int id) throws UserNotFoundException
	{
		boolean flag = userRepo.existsById(id);
		if(flag == false)
			throw new UserNotFoundException("No user found with that Id\r\n"
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
	
	public static boolean validateUsername(String userName)
    {  
        String regex = "[a-zA-Z]{3,30}$";
        Pattern p = Pattern.compile(regex);
        if (userName == null) {
            return false;
        }
        Matcher m = p.matcher(userName);
        return m.matches();
        
    }	
		
	public static boolean validateUser(User user) throws UserNotFoundException {
		boolean flag = false;
		if(user == null)
			throw new UserNotFoundException("User details cannot be blank");
		else if(!validateUsername(user.getUserName()))
			throw new UserNotFoundException("Format For UserName is Wrong\r\n"
					+ "\r\n"
					+ "Please Enter Again :\r\n"
					+ "____________________________________________________________\r\n"
					+ "\r\n"
					+ "Valid Format for UserName:\r\n"
					+ "\r\n"
					+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
					+ "[a – z] or uppercase character [A – Z].\r\n"
					+ "____________________________________________________________\r\n");
		else if(!validatePassword(user.getPassword()))
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
		else if(!validateUserType(user.getUserType()))
			throw new UserNotFoundException("Invalid User Type"); 
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateUserType(String userType) throws UserNotFoundException
	{
		boolean flag = false;
		
		if(userType == null)
			throw new UserNotFoundException("User Type cannot be blank");
		else if (!userType.matches("^[A-Za-z]\\w{3,10}+$"))
			throw new UserNotFoundException("User Type cannot contain numbers or special characters");
		else if (!(userType.equals("tenant") || userType.equals("landlord") || userType.equals("admin")
				|| userType.equals("Tenant") || userType.equals("Landlord") || userType.equals("Admin")
				|| userType.equals("TENANT") || userType.equals("LANDLORD") || userType.equals("ADMIN")))
			throw new UserNotFoundException("User Type can only be Admin or Tenant or Landlord");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateUserPassword(User user) throws UserNotFoundException {
		boolean flag = false;
		if(user == null)
			throw new UserNotFoundException("User details cannot be blank");
		else if(!(validatePassword(user.getPassword()) && validateUsername(user.getUserName())))
			throw new UserNotFoundException(" Either \"Format for New Password is Wrong\" or \"you entered Wrong user id or old Password\"\r\n"
					+ "\r\n"
					+ "_______________________________________________________________________________________________________________________\r\n"
					+ "\r\n"
					+ "Valid Format for NewPassword:\r\n"
					+ "\r\n"
					+ "Password should not contain any space.\r\n"
					+ "Password should contain at least one digit(0-9).\r\n"
					+ "Password length should be between 8 to 15 characters.\r\n"
					+ "Password should contain at least one lowercase letter(a-z).\r\n"
					+ "Password should contain at least one uppercase letter(A-Z).\r\n"
					+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)\r\n"
					+ "");
		else
			flag = true;
		return flag;
	}

}