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
	IUserRepository userRepo;
	
	@Override
	public UserDTO viewUser(int id) throws UserNotFoundException {
		User existUser = userRepo.findById(id).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user Found");
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
			throw new UserNotFoundException("No user found");
		else
			userRepo.delete(existUser);
		return UserUtils.convertToUserDto(existUser);
	}
	
	public boolean validateUserId(int id) throws UserNotFoundException
	{
		boolean flag = userRepo.existsById(id);
		if(flag == false)
			throw new UserNotFoundException("No user Found");
		return flag;
	}
	
	
	
	public static boolean validatePassword(String password)
    {
		 if (!((password.length() >= 8)
	              && (password.length() <= 10))) {
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
	
	public static boolean validUsername(String userName)
    {  
        String regex = "^[A-Za-z]\\w{3,20}$";
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
		else
			flag = true;
		return flag;
	}
	
public boolean validateUser(String username, String password) throws UserNotFoundException {
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
}