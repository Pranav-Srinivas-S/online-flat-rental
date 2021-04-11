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

import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.service.ITenantService;

@SpringBootTest
class TenantServiceImplTest {

	final static Logger LOGGER = LoggerFactory.getLogger(TenantServiceImplTest.class);
	
	@Autowired
	ITenantService service;
	
	Tenant tenant = null;
	
	FlatAddress flatAddress = null;
	
	@BeforeAll
	public static void init() {
		LOGGER.info("Tenant Testing Initiated");
	}

	@Test
	void testAddTenant01() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant01()");
		FlatAddress flatAddress = new FlatAddress(101, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha", 24, flatAddress);
		assertNotNull(service.addTenant(tenant));
	}

	@Test
	void testAddTenant02() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant02()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, null, 24, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant name cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant03() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant03()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "al", 24, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant04() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant04()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "AlphaAlphaAlphaAlphaAlphaAlphaAlpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant05() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant05()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha123", 24, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant06() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant06()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha#$", 24, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant07() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant07()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 10, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant08() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant08()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 17, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant09() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant09()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 0, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant10() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant10()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", -1, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant12() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant12()");
		flatAddress = new FlatAddress(0, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("House Number cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant13() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant13()");
		flatAddress = new FlatAddress(1, null, "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Street cannot be empty", exception.getMessage());
		}
	}
	
	
	@Test
	void testAddTenant14() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant14()");
		flatAddress = new FlatAddress(1, "street", null, "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("City cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant15() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant15()");
		flatAddress = new FlatAddress(1, "street", "city", null, 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("State cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant16() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant16()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 0, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant17() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant17()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, null);
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Country cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant18() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant18()");
		flatAddress = new FlatAddress(1, "street", "Chennai123", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant19() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant19()");
		flatAddress = new FlatAddress(1, "street", "Chennai#$", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant20() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant20()");
		flatAddress = new FlatAddress(1, "street", "city", "Tamil Nadu 123", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant21() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant21()");
		flatAddress = new FlatAddress(1, "street", "city", "Tamil_Nadu", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant22() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant22()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "India123");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant23() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant23()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "India#$");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant24() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant26()");
		flatAddress = new FlatAddress(1, "street", "city", "state", -600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant25() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant27()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 6001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
	
	@Test
	void testAddTenant26() throws TenantNotFoundException {
		LOGGER.info("Testing testAddTenant28()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 60000001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant01() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant01()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		assertNotNull(service.updateTenant(tenant));
	}
	
	@Test
	void testUpdateTenant02() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant02()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(1, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant04() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant04()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, null, 24, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant name cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant05() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant05()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "al", 24, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant06() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant06()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "AlphaAlphaAlphaAlphaAlphaAlphaAlpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant07() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant07()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha123", 24, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant08() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant08()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha#$", 24, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant09() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant09()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 10, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant10() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant10()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 17, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant11() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant11()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 0, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant12() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant12()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", -1, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant14() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant14()");
		flatAddress = new FlatAddress(0, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("House Number cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant15() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant15()");
		flatAddress = new FlatAddress(1, null, "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Street cannot be empty", exception.getMessage());
		}
	}
	
	
	@Test
	void testUpdateTenant16() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant16()");
		flatAddress = new FlatAddress(1, "street", null, "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("City cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant17() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant17()");
		flatAddress = new FlatAddress(1, "street", "city", null, 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("State cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant18() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant18()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 0, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant19() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant19()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, null);
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Country cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant20() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant20()");
		flatAddress = new FlatAddress(1, "street", "Chennai123", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant21() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant21()");
		flatAddress = new FlatAddress(1, "street", "Chennai#$", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant22() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant22()");
		flatAddress = new FlatAddress(1, "street", "city", "Tamil Nadu 123", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant23() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant23()");
		flatAddress = new FlatAddress(1, "street", "city", "Tamil_Nadu", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant24() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant24()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "India123");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant25() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant25()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "India#$");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant26() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant28()");
		flatAddress = new FlatAddress(1, "street", "city", "state", -600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant27() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant29()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 6001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateTenant28() throws TenantNotFoundException {
		LOGGER.info("Testing testUpdateTenant30()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 60000001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		try
		{
			service.updateTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}

//	@Test
//	void testDeleteTenant01() throws TenantNotFoundException {
//		LOGGER.info("Testing testDeleteTenant01()");
//		assertEquals(service.viewTenant(219).getTenantName(), service.deleteTenant(219).getTenantName());
//	}
	
	@Test
	void testDeleteTenant02() throws TenantNotFoundException {
		LOGGER.info("Testing testDeleteTenant02()");
		try
		{
			service.deleteTenant(1);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}
	
	@Test
	void testDeleteTenant03() throws TenantNotFoundException {
		LOGGER.info("Testing testDeleteTenant03()");
		try
		{
			service.deleteTenant(0);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}
	
	@Test
	void testViewTenant01() throws TenantNotFoundException {
		LOGGER.info("Testing testViewTenant01()");
		assertEquals("Alpha", service.viewTenant(210).getTenantName());
	}
	
	@Test
	void testViewTenant02() throws TenantNotFoundException {
		LOGGER.info("Testing testViewTenant02()");
		try
		{
			service.deleteTenant(1);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}
	
	@Test
	void testViewTenant03() throws TenantNotFoundException {
		LOGGER.info("Testing testViewTenant03()");
		try
		{
			service.deleteTenant(0);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}

	@Test
	void testViewAllTenant01() {
		LOGGER.info("Testing testViewAllTenant01()");
		assertNotNull(service.viewAllTenant());
	}
	
	@Test
	void testViewAllTenant02() throws TenantNotFoundException{
		LOGGER.info("Testing testViewAllTenant02()");
		try
		{
			assertNull(service.viewAllTenant());
		}
		catch (AssertionFailedError exception)
		{
			assertNotNull(service.viewAllTenant());
		}
	}
	
	@AfterAll
	public static void end() {
		LOGGER.info("Tenant Testing Terminated");
	}

}
