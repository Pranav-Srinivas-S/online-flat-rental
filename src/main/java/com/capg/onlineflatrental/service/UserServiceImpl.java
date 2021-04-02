package com.capg.onlineflatrental.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;
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
	
	static IUserRepository userRepo;

	
	@Override
	
	public UserDTO viewUser(int id) throws UserNotFoundException {
		if(validateUserId(id))
		{
			User tenantEntity = userRepo.findById(id).orElse(null);
			return UserUtils.convertToUserDto(tenantEntity);
		}
		else
			throw new UserNotFoundException(" No Users found");
	}
	

	@Override
	public List<UserDTO> viewAllUser() {
		List<User> userList = userRepo.findAll();
		return UserUtils.convertToUserDtoList(userList);
	}

	public static boolean validateUser(String username, String password) throws UserNotFoundException {
		try {
			Optional<User> user = userRepo.findByUserName(username);
			if (user.isPresent()) {
				if (username.equals(user.get().getUserName()) && (password.equals(user.get().getPassword()) )) {
					return true;
				} else {
					throw new EntityNotFoundException("Password does not match for " + username);
				}
			} else {
				throw new EntityNotFoundException("User is not there with username :" + username);
			}
		} catch (Exception e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}


	@Override
	public UserDTO addUser(User user) {
		
			User userEntity = userRepo.save(user);
			return UserUtils.convertToUserDto(userEntity);
		}

	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		if(validateUserId(user.getUserId()) && validateUser(user.getUserName(),user.getPassword()))
		{
			User userEntity = userRepo.save(user);
			return UserUtils.convertToUserDto(userEntity);
		}
		else
			throw new UserNotFoundException("No user Found");
	}

	
			

	@Override
	public UserDTO updatePassword(User user, String newpass) throws UserNotFoundException {
		try {
			Optional<User> checkuser = userRepo.findByUserName(user.getUserName());
			Optional<Boolean> checkuser1 = Optional.of(validateUser(checkuser.get().getUserName(), checkuser.get().getPassword()));
			if (checkuser1.isPresent()) {
				user.setPassword(newpass);
				User userEntity = userRepo.save(user);
				return UserUtils.convertToUserDto(userEntity);
				
			} else {
				throw new UserNotFoundException("No user found");
			}

		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}
		

	@Override
	public void removeUser(int id) throws UserNotFoundException {
		if(validateUserId(id))
		{
			userRepo.deleteById(id);
		}
		else
			throw new UserNotFoundException("No user Found");
	}

	

	public static boolean validateUserId(int id) throws UserNotFoundException
	{
		boolean flag = userRepo.existsById(id);
		if(flag == false)
			throw new UserNotFoundException("No user Found");
		return flag;
	}
	public static boolean isValid(String password)
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
	public static boolean isValidUsername(String userName)
    {
  
        
        String regex = "^[A-Za-z]\\w{5,29}$";
  
        
        Pattern p = Pattern.compile(regex);
  
        
        if (userName == null) {
            return false;
        }
  
       
        Matcher m = p.matcher(userName);
  
        return m.matches();
    }
		
		
	}


