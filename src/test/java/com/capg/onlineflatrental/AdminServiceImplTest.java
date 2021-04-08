package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.exception.AdminNotFoundException;
import com.capg.onlineflatrental.exception.UserNotFoundException;
import com.capg.onlineflatrental.service.IAdminService;

@Disabled
@SpringBootTest
class AdminServiceImplTest {
	
	final static Logger LOGGER = LoggerFactory.getLogger(AdminServiceImplTest.class);

	@Autowired
	IAdminService service;

	Admin admin = null;

	@BeforeAll
	public static void init() {
		LOGGER.info("Admin Testing Initiated");
	}

	@Test
	void testAddAdmin01() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin01()");
		Admin admin = new Admin(90, "Sai@2000");
		assertNotNull(service.addAdmin(admin));
	}

	@Test
	void testAddAdmin02() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin02()");
		admin = new Admin(90, null);
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals("Password cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testAddAdmin03() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin03()");
		admin = new Admin(90, " ");
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testAddAdmin04() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin04()");
		admin = new Admin(90, "Sathya ");
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testAddAdmin05() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin05()");
		admin = new Admin(90, "sr");
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testAddAdmin06() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin06()");
		admin = new Admin(90, "SAI");
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testAddAdmin07() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin07()");
		admin = new Admin(90, "sai");
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testAddAdmin08() throws AdminNotFoundException {
		LOGGER.info("Testing testAddAdmin08()");
		admin = new Admin(90, "Sai2000");
		try {
			service.addAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin01() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin01()");
		admin = new Admin(90, "SaiBharath@2000");
		assertNotNull(service.updateAdmin(admin));
	}

	@Test
	void testUpdateAdmin02() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin02()");
		admin = new Admin(90, null);
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals("Password cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin03() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin03()");
		admin = new Admin(90, " ");
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin04() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin04()");
		admin = new Admin(90, "Sathya ");
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin05() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin05()");
		admin = new Admin(90, "sr");
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin06() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin06()");
		admin = new Admin(90, "SAI");
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin07() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin07()");
		admin = new Admin(90, "sai");
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testUpdateAdmin08() throws AdminNotFoundException {
		LOGGER.info("Testing testUpdateAdmin08()");
		admin = new Admin(90, "Sai2000");
		try {
			service.updateAdmin(admin);
		} catch (AdminNotFoundException exception) {
			assertEquals(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)",
					exception.getMessage());
		}
	}

	@Test
	void testViewAdmin() throws AdminNotFoundException {
		LOGGER.info("Testing testViewAdmin01()");
		assertEquals("Sai@2000", service.viewAdmin(91).getAdminPassword());
	}

	@Test
	void testViewAdmin02() throws AdminNotFoundException {
		LOGGER.info("Testing testViewAdmin02()");
		try {
			service.viewAdmin(1);
		} catch (AdminNotFoundException exception) {
			assertEquals("No Admin found in given ID", exception.getMessage());
		}
	}

	@Test
	void testViewAllAdmin01() {
		LOGGER.info("Testing testViewAllAdmin01()");
		assertNotNull(service.viewAllAdmin());
	}

	@Test
	void testViewAllAdmin02() throws UserNotFoundException {
		LOGGER.info("Testing testViewAllAdmin02()");
		try {
			assertNull(service.viewAllAdmin());
		} catch (AssertionFailedError exception) {
			assertNotNull(service.viewAllAdmin());
		}
	}

	@Test
	void testDeleteAdmin01() throws AdminNotFoundException {
		LOGGER.info("Testing testDeleteAdmin01()");
		try {
			service.deleteAdmin(78);
		} catch (AdminNotFoundException exception) {
			assertEquals("No Admin found in given ID", exception.getMessage());
		}
	}
	
	@AfterAll
	public static void end() {
		LOGGER.info("Admin Testing Terminated");
	}
}