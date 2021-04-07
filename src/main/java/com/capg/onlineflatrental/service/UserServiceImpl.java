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
	public UserDTO addUser(User user) throws UserNotFoundException {
		User userEntity = null;
		User existUser = null;
		existUser = userRepo.findByUserName(user.getUserName());
		if(existUser != null)
			throw new UserNotFoundException("User Name already exists, Try anouther name");
		else if(!validateUser(user))
			throw new UserNotFoundException("Invalid User Details");
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
		else if(!updateUser(user.getUserId(), user.getUserName(), user.getPassword()))
			throw new UserNotFoundException("User Details Dont Match");
		else if(!validateUserType(user.getUserType()))
			throw new UserNotFoundException("User Details Dont Match");
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
			throw new UserNotFoundException("No user found with given ID");
		else if(!(updatePassword(user.getUserId(), user.getUserName(), user.getPassword(),user.getUserType()) && validatePassword(newpass)) && validateUserType(user.getUserType()))
			throw new UserNotFoundException("User Details Don't Match");
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
	
	@Override
	public boolean checkUser(String userName, String password) throws UserNotFoundException {
		boolean flag = false;
		User user = userRepo.findByUserName(userName);
		if(user == null)
			throw new UserNotFoundException("Invalid User Name");
		else if (user.getPassword().equals(password))
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
	
	public static boolean validateUsername(String userName) throws UserNotFoundException
    {  
		boolean flag = false;
		if(userName == null)
			throw new UserNotFoundException("User Name cannot be empty");
		else if(!userName.matches("^[a-zA-Z]+$"))
			throw new UserNotFoundException(usernameformat);
		else if(userName.length()<3 || userName.length()>40)
			throw new UserNotFoundException(usernameformat);
		else
			flag = true;
		return flag;
    }	
	
	public static boolean validatePassword(String Password) throws UserNotFoundException
    {  
		boolean flag = false;
		if(Password == null)
			throw new UserNotFoundException(passwordformat);
		else if(!Password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"))
			throw new UserNotFoundException(passwordformat);
		else
			flag = true;
		return flag;
    }
	
	public static boolean validateUserType(String userType) throws UserNotFoundException
	{
		boolean flag = false;
		
		if(userType == null)
			throw new UserNotFoundException("User Type cannot be blank");
		else if (!userType.matches("^[A-Za-z]+$"))
			throw new UserNotFoundException("User Type cannot contain numbers or special characters");
		else if (!(userType.equals("tenant") || userType.equals("landlord") || userType.equals("admin")
				|| userType.equals("Tenant") || userType.equals("Landlord") || userType.equals("Admin")
				|| userType.equals("TENANT") || userType.equals("LANDLORD") || userType.equals("ADMIN")))
			throw new UserNotFoundException("User Type can only be Admin or Tenant or Landlord");
		else
			flag = true;
		return flag;
	}
		
	public static boolean validateUser(User user) throws UserNotFoundException {
		boolean flag = false;
		if(user == null)
			throw new UserNotFoundException("User details cannot be blank");
		else if(!(validateUsername(user.getUserName()) && validatePassword(user.getPassword()) && validateUserType(user.getUserType())))
			throw new UserNotFoundException("Invalid User Details"); 
		else
			flag = true;
		return flag;
	}
	
	public boolean updateUser(int id, String userName, String password) throws UserNotFoundException {
		boolean flag = false;
		User user = userRepo.findById(id).orElse(null);
		if(!validateUserId(id))
			throw new UserNotFoundException("No User available in given ID");
		else if(user == null)
			throw new UserNotFoundException("Invalid User Name");
		else if  (!user.getUserName().equals(userName) && user != null)
		throw new UserNotFoundException("Invalid User Name");
		else if (user.getPassword().equals(password) && user != null)
			flag = true;
		else 
			throw new UserNotFoundException("Password does not Match");
		return flag;		
	}
	
	public boolean updatePassword(int id, String userName, String password,String UserType) throws UserNotFoundException {
		boolean flag = false;
		User user = userRepo.findByIdAndName(id, userName);
		if(!validateUserId(id))
			throw new UserNotFoundException("No User available in given ID");
		else if(user == null)
			throw new UserNotFoundException("Invalid User Name");
		else if (!user.getUserType().equals(UserType) && user != null)
			throw new UserNotFoundException("Invalid User Type");
		else if (user.getPassword().equals(password) && user != null)
			flag = true;
		else 
			throw new UserNotFoundException("Password does not Match");
		return flag;
	}
	static String passwordformat ="Format for password is Wrong\r\n"
			+ "\r\n"
			+ "Please Enter Password Again\r\n"
			+ "\r\n"
			+ "Password cannot be empty\\r\\n"
			+ "Password must contain at least one digit [0-9].\r\n"
			+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
			+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
			+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
			+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
			+ ""  ;
	static String usernameformat ="Format For UserName is Wrong\r\n"
			+ "\r\n"
			+ "Please Enter Again :\r\n"
			+ "____________________________________________________________\r\n"
			+ "\r\n"
			+ "Valid Format for UserName:\r\n"
			+ "\r\n"
			+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
			+ "[a – z] or uppercase character [A – Z].\r\n"
			+ "User Name length should be in range 3 to 40."
			+ "\r\n";


public boolean checkUser(int id, String userName, String password) throws UserNotFoundException {
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
}
