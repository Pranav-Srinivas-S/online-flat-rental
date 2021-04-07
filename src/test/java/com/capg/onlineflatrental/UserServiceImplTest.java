package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.service.IUserService;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	IUserService service;
	
	User user = null;

	@BeforeAll
	public static void init() {
		//System.out.println("Before All Executed");
	}
	
	@Test
	void testAddUser01() throws UserNotFoundException {
		User user = new User(1, "Sathya", "Sai@2000", "Admin");
		assertNotNull(service.addUser(user));
	}
	
	@Test
	void testAddUser02() throws UserNotFoundException {
		 user = new User(1, null, "Sai@2000", "Admin");
		try
		{
			service.addUser(user);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("User Name cannot be empty", exception.getMessage());
		}
	}
		
		@Test
		void testAddUser03() throws UserNotFoundException {
			 user = new User(1, "Sathya12", "Sai@2000", "Admin");
			try
			{
				service.addUser(user);
			}
			catch(UserNotFoundException exception)
			{
				assertEquals("Format For UserName is Wrong\r\n"
						+ "\r\n"
						+ "Please Enter Again :\r\n"
						+ "____________________________________________________________\r\n"
						+ "\r\n"
						+ "Valid Format for UserName:\r\n"
						+ "\r\n"
						+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
						+ "[a – z] or uppercase character [A – Z].\r\n"
						+ "User Name length should be in range 3 to 40."
						+ "\r\n", exception.getMessage());
			}
	}
		@Test
		void testAddUser04() throws UserNotFoundException {
			 user = new User(1, "Sathya@", "Sai@2000", "Admin");
			try
			{
				service.addUser(user);
			}
			catch(UserNotFoundException exception)
			{
				assertEquals("Format For UserName is Wrong\r\n"
						+ "\r\n"
						+ "Please Enter Again :\r\n"
						+ "____________________________________________________________\r\n"
						+ "\r\n"
						+ "Valid Format for UserName:\r\n"
						+ "\r\n"
						+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
						+ "[a – z] or uppercase character [A – Z].\r\n"
						+ "User Name length should be in range 3 to 40."
						+ "\r\n", exception.getMessage());
			}
	}
		@Test
		void testAddUser05() throws UserNotFoundException {
			 user = new User(1, "Sa", "Sai@2000", "Admin");
			try
			{
				service.addUser(user);
			}
			catch(UserNotFoundException exception)
			{
				assertEquals("Format For UserName is Wrong\r\n"
						+ "\r\n"
						+ "Please Enter Again :\r\n"
						+ "____________________________________________________________\r\n"
						+ "\r\n"
						+ "Valid Format for UserName:\r\n"
						+ "\r\n"
						+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
						+ "[a – z] or uppercase character [A – Z].\r\n"
						+ "User Name length should be in range 3 to 40."
						+ "\r\n", exception.getMessage());
			}
	}

		@Test
		void testAddUser06() throws UserNotFoundException {
			 user = new User(1, "Man", null, "Admin");
			try
			{
				service.addUser(user);
			}
			catch(UserNotFoundException exception)
			{
				assertEquals("Format for password is Wrong\r\n"
						+ "\r\n"
						+ "Please Enter Password Again\r\n"
						+ "\r\n"
						+ "Password cannot be empty\\r\\n"
						+ "Password must contain at least one digit [0-9].\r\n"
						+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
						+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
						+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
						+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
						+ "" , exception.getMessage());
			}
		}
		
		@Test
		void testAddUser07() throws UserNotFoundException {
			 user = new User(1, "Man", "r", "Admin");
			try
			{
				service.addUser(user);
			}
			catch(UserNotFoundException exception)
			{
				assertEquals("Format for password is Wrong\r\n"
						+ "\r\n"
						+ "Please Enter Password Again\r\n"
						+ "\r\n"
						+ "Password cannot be empty\\r\\n"
						+ "Password must contain at least one digit [0-9].\r\n"
						+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
						+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
						+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
						+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
						+ "" , exception.getMessage());
			}
		}
			
			@Test
			void testAddUser08() throws UserNotFoundException {
				 user = new User(1, "Man", "Saibharath", "Admin");
				try
				{
					service.addUser(user);
				}
				catch(UserNotFoundException exception)
				{
					assertEquals("Format for password is Wrong\r\n"
							+ "\r\n"
							+ "Please Enter Password Again\r\n"
							+ "\r\n"
							+ "Password cannot be empty\\r\\n"
							+ "Password must contain at least one digit [0-9].\r\n"
							+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
							+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
							+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
							+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
							+ "" , exception.getMessage());
				}
			}
		
@Test
void testAddUser09() throws UserNotFoundException {
	 user = new User(1, "Man", "Sai", "Admin");
	try
	{
		service.addUser(user);
	}
	catch(UserNotFoundException exception)
	{
		assertEquals("Format for password is Wrong\r\n"
				+ "\r\n"
				+ "Please Enter Password Again\r\n"
				+ "\r\n"
				+ "Password cannot be empty\\r\\n"
				+ "Password must contain at least one digit [0-9].\r\n"
				+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
				+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
				+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
				+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
				+ "" , exception.getMessage());
	}
}
@Test
void testAddUser10() throws UserNotFoundException {
	 user = new User(1, "Man", "SAI@2000", "Admin");
	try
	{
		service.addUser(user);
	}
	catch(UserNotFoundException exception)
	{
		assertEquals("Format for password is Wrong\r\n"
				+ "\r\n"
				+ "Please Enter Password Again\r\n"
				+ "\r\n"
				+ "Password cannot be empty\\r\\n"
				+ "Password must contain at least one digit [0-9].\r\n"
				+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
				+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
				+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
				+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
				+ "" , exception.getMessage());
	}
}
	@Test
	void testAddUser11() throws UserNotFoundException {
		 user = new User(1, "Man", "sai@2000", "Admin");
		try
		{
			service.addUser(user);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("Format for password is Wrong\r\n"
					+ "\r\n"
					+ "Please Enter Password Again\r\n"
					+ "\r\n"
					+ "Password cannot be empty\\r\\n"
					+ "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
					+ "" , exception.getMessage());
		}
	}
	@Test
	void testAddUser12() throws UserNotFoundException {
		 user = new User(1, "Man", "Sai2000", "Admin");
		try
		{
			service.addUser(user);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("Format for password is Wrong\r\n"
					+ "\r\n"
					+ "Please Enter Password Again\r\n"
					+ "\r\n"
					+ "Password cannot be empty\\r\\n"
					+ "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
					+ "" , exception.getMessage());
		}
}
	
	@Test
	void testAddUser13() throws UserNotFoundException {
		 user = new User(1, "Man", "Sai@2000", null);
		try
		{
			service.addUser(user);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("User Type cannot be blank", exception.getMessage());
}
	}
	
	@Test
	void testAddUser14() throws UserNotFoundException {
		 user = new User(1, "Man", "Sai@2000", "user");
		try
		{
			service.addUser(user);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("User Type can only be Admin or Tenant or Landlord", exception.getMessage());}
}
		@Test
		void testAddUser15() throws UserNotFoundException {
			 user = new User(1, "Man", "Sai@2000", "@@@");
			try
			{
				service.addUser(user);
			}
			catch(UserNotFoundException exception)
			{
				assertEquals("User Type cannot contain numbers or special characters", exception.getMessage());
	}
	}
	
	@Test
	void testUpdateUser01() throws UserNotFoundException {
		
		user = new User(71, "Manasaaaaa", "Manu@1999", "landlord");
		assertNotNull(service.updateUser(user));
	}
	
	@Test
	void testUpdateUser02() throws UserNotFoundException {
		
		user = new User(710, "Manasa", "Manu@1999", "Admin");
		try
		{
			service.updateUser(user);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("No user found", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateUser03() throws UserNotFoundException {
		
		user = new User(50, "Manasaaaaaa", "Sai@2000", "Admin");
		try
		{
			service.updateUser(user);
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateUser04() throws UserNotFoundException {
		
		user = new User(50, "SaiBharath", "Sai@20000", "user");
	
		try
		{
			service.updateUser(user);
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("User Type can only be Admin or Tenant or Landlord", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateUser05() throws UserNotFoundException {
		
		user = new User(50, "SaiBharath", "Sai@20000", "@@");
		
		try
		{
			service.updateUser(user);
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("User Type cannot contain numbers or special characters", exception.getMessage());
		}
	}
	

		
	@Test
	void testUpdatePassword01() throws UserNotFoundException {
		
		user = new User(500, "SaiBharath", "Sai@2000", "landlord");
		
		try
		{
			service.updatePassword(user, "Sai@200000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("No user found with given ID", exception.getMessage());
		}
	}
	
	@Test
	void testUpdatePassword02() throws UserNotFoundException {
		
		user = new User(50, "Sai_Bharath", "Sai@20000", "landlord");
		
		try
		{
			service.updatePassword(user, "Sai@200000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}
	
	@Test
	void testUpdatePassword03() throws UserNotFoundException {
		
		user = new User(50, "SaiBharath", "Sai@20003", "Admin");
		
		try
		{
			service.updatePassword(user, "Sai@200000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Password does not Match", exception.getMessage());
		}
	}
	
	@Test
	void testUpdatePassword04() throws UserNotFoundException {
		
		user = new User(50, "Sai_Bharath", "Sai@20000", "Admin");
		
		try
		{
			service.updatePassword(user, "Sai@200000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}
	
	@Test
	void testUpdatePassword05() throws UserNotFoundException {
		
		user = new User(50, "SaiBharath", "Sai@20000", "lanlord");
		
		try
		{
			service.updatePassword(user, "Sai@200000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Invalid User Type", exception.getMessage());
		}
	}
	
	@Test
	void testCheckUser01() throws UserNotFoundException{
		assertEquals(true,service.checkUser("Manu","Sai@200000"));
	}
	
	@Test
	void testCheckUser02() throws UserNotFoundException{
		

		try
		{
			service.checkUser("Manu", "Sai@2000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Password does not Match", exception.getMessage());
		}
		
	}
	

	@Test
	void testCheckUser03() throws UserNotFoundException{
		

		try
		{
			service.checkUser("Man", "Sai@2000");
		}
		
		catch(UserNotFoundException exception)
		{
			assertEquals("Invalid User Name", exception.getMessage());
		}
		
	}
	
	
	@Test
	void testViewUser() throws UserNotFoundException {
		assertEquals("SaiBharath", service.viewUser(50).getUserName());
	}
	
	@Test
	void testViewUser02() throws UserNotFoundException {
		try
		{
			service.viewUser(1);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId", exception.getMessage());
		}
	}
	
	@Test
	void testViewUser03() throws UserNotFoundException {
		try
		{
			service.viewUser(1);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId", exception.getMessage());
		}
	}
	
	@Test
	void testViewAllUser01() {
		assertNotNull(service.viewAllUser());
	}
	
	@Test
	void testViewAllUser02() throws UserNotFoundException{
		try
		{
			assertNull(service.viewAllUser());
		}
		catch (AssertionFailedError exception)
		{
			assertNotNull(service.viewAllUser());
		}
	}


	@Test
	void testRemoveuser01() throws UserNotFoundException {
		try
		{
			service.removeUser(78);
		}
		catch(UserNotFoundException exception)
		{
			assertEquals("No user found with that Id\r\n"
					+ "\r\n"
					+ "Enter valid UserId", exception.getMessage());
		}
		}

@Test
void testRemoveuser02() throws UserNotFoundException {
	try
	{
		service.removeUser(1);
	}
	catch(UserNotFoundException exception)
	{
		assertEquals("No user found with that Id\r\n"
				+ "\r\n"
				+ "Enter valid UserId", exception.getMessage());
	}
}

@Test
void testRemoveuser03() throws UserNotFoundException {
	try
	{
		service.removeUser(100);
	}
	catch(UserNotFoundException exception)
	{
		assertEquals("No user found with that Id\r\n"
				+ "\r\n"
				+ "Enter valid UserId", exception.getMessage());
	}
}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
