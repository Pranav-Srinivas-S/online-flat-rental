package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.exception.LoginNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.service.ILoginService;

@SpringBootTest
class LoginServiceImplTest {

	@Autowired
	ILoginService service;

	User user = null;

	@BeforeAll
	public static void init() {
		// System.out.println("Before All Executed");
	}
	@Test
	void testCheckUser01() throws  UserNotFoundException {
		try
		{
			service.Login(user);
		}catch(LoginNotFoundException exception)
		{
			assertEquals("No user found with that Id and password"
					+"\r\n" + "Enter valid UserId and Password", exception.getMessage());
		}
		
	}

	
}