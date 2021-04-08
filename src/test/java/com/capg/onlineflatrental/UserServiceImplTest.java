package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.service.IUserService;


@SpringBootTest
class UserServiceImplTest {

	static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);

	@Autowired
	IUserService service;

	User user = null;

	@BeforeAll
	public static void init() {
		LOGGER.info("User Testing Initiated");
	}

	@Test
	void testAddUser01() throws UserNotFoundException {
	 LOGGER.info("Testing testAddUser01()");
		User user = new User(1, "Deep", "Sai@2000", "Admin");
		assertNotNull(service.addUser(user));
	}

	@Test
	void testAddUser02() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser02()");
		user = new User(1, null, "Sai@2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddUser03() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser03()");
		user = new User(1, "Sathya12", "Sai@2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format For UserName is Wrong\r\n" + "\r\n" + "Please Enter Again :\r\n"
					+ "____________________________________________________________\r\n" + "\r\n"
					+ "Valid Format for UserName:\r\n" + "\r\n"
					+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
					+ "[a – z] or uppercase character [A – Z].\r\n" + "User Name length should be in range 3 to 40."
					+ "\r\n", exception.getMessage());
		}
	}

	@Test
	void testAddUser04() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser04()");
		user = new User(1, "Sathya@", "Sai@2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format For UserName is Wrong\r\n" + "\r\n" + "Please Enter Again :\r\n"
					+ "____________________________________________________________\r\n" + "\r\n"
					+ "Valid Format for UserName:\r\n" + "\r\n"
					+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
					+ "[a – z] or uppercase character [A – Z].\r\n" + "User Name length should be in range 3 to 40."
					+ "\r\n", exception.getMessage());
		}
	}

	@Test
	void testAddUser05() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser05()");
		user = new User(1, "Sa", "Sai@2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Name length must be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testAddUser06() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser06()");
		user = new User(1, "Sathyaa", null, "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Password cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddUser07() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser07()");
		user = new User(1, "Sathyaa", "r", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format for password is Wrong\r\n" + "\r\n" + "Please Enter Password Again\r\n" + "\r\n"
					+ "Password cannot be empty\\r\\n" + "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters." + "",
					exception.getMessage());
		}
	}

	@Test
	void testAddUser08() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser08()");
		user = new User(1, "Sathyaa", "Saibharath", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format for password is Wrong\r\n" + "\r\n" + "Please Enter Password Again\r\n" + "\r\n"
					+ "Password cannot be empty\\r\\n" + "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters." + "",
					exception.getMessage());
		}
	}

	@Test
	void testAddUser09() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser09()");
		user = new User(1, "Sathyaa", "Sai2", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format for password is Wrong\r\n" + "\r\n" + "Please Enter Password Again\r\n" + "\r\n"
					+ "Password cannot be empty\\r\\n" + "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters." + "",
					exception.getMessage());
		}
	}

	@Test
	void testAddUser10() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser10()");
		user = new User(1, "Sathyaa", "SAI@2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format for password is Wrong\r\n" + "\r\n" + "Please Enter Password Again\r\n" + "\r\n"
					+ "Password cannot be empty\\r\\n" + "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters." + "",
					exception.getMessage());
		}
	}

	@Test
	void testAddUser11() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser11()");
		user = new User(1, "Sathyaa", "sai@2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format for password is Wrong\r\n" + "\r\n" + "Please Enter Password Again\r\n" + "\r\n"
					+ "Password cannot be empty\\r\\n" + "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters." + "",
					exception.getMessage());
		}
	}

	@Test
	void testAddUser12() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser12()");
		user = new User(1, "Sathyaa", "Sai2000", "Admin");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Format for password is Wrong\r\n" + "\r\n" + "Please Enter Password Again\r\n" + "\r\n"
					+ "Password cannot be empty\\r\\n" + "Password must contain at least one digit [0-9].\r\n"
					+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
					+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
					+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
					+ "Password must contain a length of at least 8 characters and a maximum of 20 characters." + "",
					exception.getMessage());
		}
	}

	@Test
	void testAddUser13() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser13()");
		user = new User(1, "Sathyaa", "Sai@2000", null);
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Type cannot be blank", exception.getMessage());
		}
	}

	@Test
	void testAddUser14() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser14()");
		user = new User(1, "Sathyaa", "Sai@2000", "user");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Type can only be Admin or Tenant or Landlord", exception.getMessage());
		}
	}

	@Test
	void testAddUser15() throws UserNotFoundException {
		LOGGER.info("Testing testAddUser15()");
		user = new User(1, "Sathyaa", "Sai@2000", "@@@");
		try {
			service.addUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Type cannot contain numbers or special characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateUser01() throws UserNotFoundException {
		LOGGER.info("Testing testUpdateUser01()");
		user = new User(50, "SaiBharath", "Sai@200000", "landlord");
		assertNotNull(service.updateUser(user));
	}

	@Test
	void testUpdateUser02() throws UserNotFoundException {
		LOGGER.info("Testing testUpdateUser02()");
		user = new User(510, "SaiBharath", "Sai@20000", "landlord");
		try {
			service.updateUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("No user found", exception.getMessage());
		}
	}

	@Test
	void testUpdateUser03() throws UserNotFoundException {
		LOGGER.info("Testing testUpdateUser03()");
		user = new User(50, "SaigggBharath", "Sai@20000", "landlord");
		try {
			service.updateUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}

	@Test
	void testUpdateUser04() throws UserNotFoundException {
		LOGGER.info("Testing testUpdateUser04()");
		user = new User(50, "SaiBharath", "Sai@200000", "user");
		try {
			service.updateUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Type can only be Admin or Tenant or Landlord", exception.getMessage());
		}
	}

	@Test
	void testUpdateUser05() throws UserNotFoundException {
		LOGGER.info("Testing testUpdateUser05()");
		user = new User(50, "SaiBharath", "Sai@200000", "@#$");
		try {
			service.updateUser(user);
		} catch (UserNotFoundException exception) {
			assertEquals("User Type cannot contain numbers or special characters", exception.getMessage());
		}
	}

	@Test
	void testUpdatePassword01() throws UserNotFoundException {
		LOGGER.info("Testing testUpdatePassword01()");
		user = new User(5000, "SaiBharath", "Sai@20000", "landlord");
		try {
			service.updatePassword(user, "Sai@200000");
		} catch (UserNotFoundException exception) {
			assertEquals("No user found with given ID", exception.getMessage());
		}
	}

	@Test
	void testUpdatePassword02() throws UserNotFoundException {
		LOGGER.info("Testing testUpdatePassword02()");
		user = new User(50, "SailklkBharath", "Sai@20000", "landlord");
		try {
			service.updatePassword(user, "Sai@200000");
		} catch (UserNotFoundException exception) {
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}

	@Test
	void testUpdatePassword03() throws UserNotFoundException {
		LOGGER.info("Testing testUpdatePassword03()");
		user = new User(80, "Sathya", "SathyaLOLOLJ", "Admin");
		try {
			service.updatePassword(user, "Sai@200000");
		} catch (UserNotFoundException exception) {
			assertEquals("Password does not Match", exception.getMessage());
		}
	}

	@Test
	void testUpdatePassword04() throws UserNotFoundException {
		LOGGER.info("Testing testUpdatePassword04()");
		user = new User(80, "Sathya", "Sai@200000", "landlord");
		try {
			service.updatePassword(user, "Sai@200000");
		} catch (UserNotFoundException exception) {
			assertEquals("Invalid User Type", exception.getMessage());
		}
	}

	@Test
	void testValidateUser01() throws UserNotFoundException {
		LOGGER.info("Testing testValidateUser01()");
		assertEquals(true, service.validateUser("Manu", "Sai@200000"));
	}

	@Test
	void testValidateUser02() throws UserNotFoundException {
		LOGGER.info("Testing testValidateUser02()");
		try {
			service.validateUser("Manu", "Sai@2000");
		} catch (UserNotFoundException exception) {
			assertEquals("Password does not Match", exception.getMessage());
		}
	}

	@Test
	void testValidateUser03() throws UserNotFoundException {
		LOGGER.info("Testing testValidateUser03()");
		try {
			service.validateUser("Man", "Sai@2000");
		} catch (UserNotFoundException exception) {
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}

	@Test
	void testViewUser01() throws UserNotFoundException {
		LOGGER.info("Testing testViewUser01()");
		assertEquals("SaiBharath", service.viewUser(50).getUserName());
	}

	@Test
	void testViewUser02() throws UserNotFoundException {
		LOGGER.info("Testing testViewUser02()");
		try {
			service.viewUser(1);
		} catch (UserNotFoundException exception) {
			assertEquals("No user found with that Id\r\n" + "\r\n" + "Enter valid UserId", exception.getMessage());
		}
	}

	@Test
	void testViewUser03() throws UserNotFoundException {
		LOGGER.info("Testing testViewUser03()");
		try {
			service.viewUser(1);
		} catch (UserNotFoundException exception) {
			assertEquals("No user found with that Id\r\n" + "\r\n" + "Enter valid UserId", exception.getMessage());
		}
	}

	@Test
	void testViewAllUser01() {
		LOGGER.info("Testing testViewAllUser01()");
		assertNotNull(service.viewAllUser());
	}

	@Test
	void testViewAllUser02() throws UserNotFoundException {
		LOGGER.info("Testing testViewAllUser02()");
		try {
			assertNull(service.viewAllUser());
		} catch (AssertionFailedError exception) {
			assertNotNull(service.viewAllUser());
		}
	}

	@AfterAll
	public static void end() {
		LOGGER.info("User Testing Terminated");
	}

}