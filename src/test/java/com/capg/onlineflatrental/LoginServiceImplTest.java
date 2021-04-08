package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.service.ILoginService;

@SpringBootTest
class LoginServiceImplTest {

	final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImplTest.class);
	
	@Autowired
	ILoginService service;

	User user = new User();

	@BeforeAll
	public static void init() {
		LOGGER.info("Tenant Testing Initiated");
	}

	@Test
	void testCheckUser01() throws LoginNotFoundException, UserNotFoundException {
		LOGGER.info("Testing testCheckUser01()");
		user.setUserId(240);
		user.setUserName("SaiBharath");
		user.setPassword("Sai@2000");
		assertEquals(true, service.Login(user));
	}
	
	@Test
	void testCheckUser02() throws LoginNotFoundException, UserNotFoundException {
		LOGGER.info("Testing testCheckUser02()");
		user = null;
		try {
		service.Login(user);
		}
		catch (LoginNotFoundException exception) {
			assertEquals("User Details cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	void testCheckUser03() throws LoginNotFoundException, UserNotFoundException {
		LOGGER.info("Testing testCheckUser03()");
		user.setUserId(240);
		user.setUserName("SaiBharath");
		user.setPassword("sai2000");
		try {
		service.Login(user);
		}
		catch (UserNotFoundException exception) {
			assertEquals("Password does not Match", exception.getMessage());
		}
	}

	@Test
	void testCheckUser04() throws LoginNotFoundException, UserNotFoundException {
		LOGGER.info("Testing testCheckUser04()");
		user.setUserId(240);
		user.setUserName("sai");
		user.setPassword("Sai@2000");
		try {
		service.Login(user);
		}
		catch (UserNotFoundException exception) {
			assertEquals("Invalid User Name", exception.getMessage());
		}
	}
	
	@AfterAll
	public static void end() {
		LOGGER.info("Login Testing Terminated");
	}
}
//