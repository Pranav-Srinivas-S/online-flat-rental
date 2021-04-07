package com.capg.onlineflatrental.service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.model.UserDTO;
import com.capg.onlineflatrental.repository.IUserRepository;
import com.capg.onlineflatrental.util.UserUtils;

/*
 * Author : RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
 * Version : 1.0
 * Date : 03-04-2021
 * Description : It is a user service class that provides the services to view user,view all users, validate user,add a user,
 *          	 remove a user, update the user, and update the password
*/

@Service
public class UserServiceImpl implements IUserService{
	
	final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired	
	private IUserRepository userRepo;
	
	/*
	 * Method:                          	viewUser
     *Description:                      	To display the user by Id.
	     *@param id:                        id of the user.
		 *@returns User                  - 	if user with Id presents it returns user else throws UserNotFoundException
		 *@throws UserNotFoundException  -  It is raised due to invalid  UserId 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public UserDTO viewUser(int id) throws UserNotFoundException {
		LOGGER.info("viewUser() service is initiated");
		User existUser = userRepo.findById(id).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId");
		LOGGER.info("viewUser() service is executed");
		return UserUtils.convertToUserDto(existUser);
	}
	
	/*
	 * Method:                          	viewAllUser
     *Description:                      	To display all the users by Id.
		 *@returns List<UserDTO>         - 	it displays all the user details
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public List<UserDTO> viewAllUser() {
		LOGGER.info("viewAllUser() service is initiated");
		List<User> userList = userRepo.findAll();
		LOGGER.info("viewAllUser() service is executed");
		return UserUtils.convertToUserDtoList(userList);
	}
	
	/*
	 * Method:                          	validateUser
     *Description:                      	To check the user name and password is matching or not.
	     *@param username:                  user name for validating user.
	     *@param password:                  password for validating user.
		 *@returns User                  - 	it will check the user details matching successfully or not.
		 *@throws UserNotFoundException  -  It is raised due to mismatch of user details. 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public boolean validateUser(String userName, String password) throws UserNotFoundException {
		LOGGER.info("validateUser() service is initiated");
		boolean flag = false;
		User user = userRepo.findByUserName(userName);
		if(user == null)
			throw new UserNotFoundException("Invalid User Name");
		else if (user.getPassword().equals(password))
			flag = true;
		else
			throw new UserNotFoundException("Password does not Match");
		LOGGER.info("validateUser() service has executed");
		return flag;
		
	}
	
	/*
	 * Method:                          	addUser
     *Description:                      	it is used to add the user details
	     *@param User                       it is User's reference variable.
		 *@returns User                  - 	it will return the user details
		 *@throws UserNotFoundException  -  It is raised due to invalid User name format and invalid user password format and if user name already exists 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public UserDTO addUser(User user) throws UserNotFoundException {
		LOGGER.info("addUser() service is initiated");
		User userEntity = null;
		User existUser = null;
		existUser = userRepo.findByUserName(user.getUserName());
		if(existUser != null)
			throw new UserNotFoundException("User Name already exists, Try anouther name");
		else if(!validateaddUser(user))
			throw new UserNotFoundException("Invalid User Details");
		else
			userEntity = userRepo.save(user);
		LOGGER.info("addUser() service has executed");
		return UserUtils.convertToUserDto(userEntity);
	}
	
	/*
	 * Method:                          	updateUser
     *Description:                      	To update the user details.
	     *@param User                       it is User's reference variable.
		 *@returns User                  - 	it will return the user updated details.
		 *@throws UserNotFoundException  -  It is raised due to mismatch of user details. 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public UserDTO updateUser(User user) throws UserNotFoundException {
		LOGGER.info("updateUser() service is initiated");
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
		LOGGER.info("updateUser() service has executed");
		return UserUtils.convertToUserDto(userEntity);
	}
	
	/*
	 * Method:                          	updatePassword
     *Description:                      	To update the user password details.
	     *@param User                       it is User's reference variable.
		 *@returns User                  - 	it will return the user updated password details.
		 *@throws UserNotFoundException  -  It is raised due to mismatch of user details. 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public UserDTO updatePassword(User user, String newpass) throws UserNotFoundException {
		LOGGER.info("updatePassword() service is initiated");
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
		LOGGER.info("updatePassword() service has executed");
		return UserUtils.convertToUserDto(userEntity);
	}
	
	/*
	 * Method:                          	removeUser
     *Description:                      	To remove the user details.
	     *@param Id                         it will remove users based on id.
		 *@returns User                  - 	it will return the user updated details.
		 *@throws UserNotFoundException  -  It is raised due to mismatch of user details. 
     *Created By                         -  RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH 
     *Created Date                       -  03-04-2021                           	 
	 */
	
	@Override
	public UserDTO removeUser(int id) throws UserNotFoundException {
		LOGGER.info("removeUser() service is initiated");
		User existUser = userRepo.findById(id).orElse(null);
		if(existUser == null)
			throw new UserNotFoundException("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId");
		else
			userRepo.delete(existUser);
		LOGGER.info("removeUser() service is executed");
		return UserUtils.convertToUserDto(existUser);
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
		
	public static boolean validateaddUser(User user) throws UserNotFoundException {
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
