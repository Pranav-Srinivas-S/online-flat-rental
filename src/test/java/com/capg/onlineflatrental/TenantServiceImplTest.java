package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.service.ITenantService;

@SpringBootTest
class TenantServiceImplTest {

	@Autowired
	ITenantService service;
	
	Tenant tenant = null;
	
	FlatAddress flatAddress = null;
	
	@BeforeAll
	public static void init() {
		//System.out.println("Before All Executed");
	}

	@Test
	void testAddTenant01() throws TenantNotFoundException {
		FlatAddress flatAddress = new FlatAddress(101, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha", 24, flatAddress);
		assertNotNull(service.addTenant(tenant));
	}

	@Test
	void testAddTenant02() throws TenantNotFoundException {
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
	void testAddTenant26() throws TenantNotFoundException {
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
	void testAddTenant27() throws TenantNotFoundException {
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
	void testAddTenant28() throws TenantNotFoundException {
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
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		tenant = new Tenant(210, "Alpha", 40, flatAddress);
		assertNotNull(service.updateTenant(tenant));
	}
	
	@Test
	void testUpdateTenant02() throws TenantNotFoundException {
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
	void testUpdateTenant28() throws TenantNotFoundException {
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
	void testUpdateTenant29() throws TenantNotFoundException {
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
	void testUpdateTenant30() throws TenantNotFoundException {
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

	@Test
	void testDeleteTenant01() throws TenantNotFoundException {
		assertEquals(service.viewTenant(219).getTenantName(), service.deleteTenant(219).getTenantName());
	}
	
	@Test
	void testDeleteTenant02() throws TenantNotFoundException {
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
	void testViewTenant() throws TenantNotFoundException {
		assertEquals("Alpha", service.viewTenant(210).getTenantName());
	}
	
	@Test
	void testViewTenant02() throws TenantNotFoundException {
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
		assertNotNull(service.viewAllTenant());
	}
	
	@Test
	void testViewAllTenant02() throws TenantNotFoundException{
		try
		{
			assertNull(service.viewAllTenant());
		}
		catch (AssertionFailedError exception)
		{
			assertNotNull(service.viewAllTenant());
		}
	}

}
