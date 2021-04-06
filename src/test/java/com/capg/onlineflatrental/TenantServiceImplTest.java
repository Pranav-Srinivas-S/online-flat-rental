package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
	
	@BeforeAll
	public static void init() {
		//System.out.println("Before All Executed");
	}

//	@Test
//	void testAddTenant01() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
//		Tenant tenant = new Tenant(1, "Alpha", 24, flatAddress);
//		assertNotNull(service.addTenant(tenant));
//	}

	@Test
	void testAddTenant02() throws TenantNotFoundException {
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, null, 24, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "al", 24, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "AlphaAlphaAlphaAlphaAlphaAlpha", 40, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha123", 24, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha#$", 24, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha", 10, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha", 17, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha", 0, flatAddress);
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
		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		Tenant tenant = new Tenant(1, "Alpha", -1, flatAddress);
		try
		{
			service.addTenant(tenant);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
//	@Test
//	void testAddTenant2() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(0, null, null, null, 0, null);
//		Tenant tenant = new Tenant(0, null, 0, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertNotNull(exception);
//		}
//	}

//	@Test
//	void testAddTenant4() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(0, "street", "city", "state", 600001, "country");
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("House Number cannot be 0 or negative", exception.getMessage());
//		}
//	}
	
//	@Test
//	void testAddTenant5() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, null, "city", "state", 600001, "country");
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("Street cannot be empty", exception.getMessage());
//		}
//	}	

//	@Test
//	void testAddTenant6() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, "street", null, "state", 600001, "country");
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("City cannot be empty", exception.getMessage());
//		}
//	}	

//	@Test
//	void testAddTenant7() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, "street", "city", null, 600001, "country");
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("State cannot be empty", exception.getMessage());
//		}
//	}
	
//	@Test
//	void testAddTenant8() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 0, "country");
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
//		}
//	}
	
//	@Test
//	void testAddTenant9() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", -1, "country");
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
//		}
//	}
	
//	@Test
//	void testAddTenant10() throws TenantNotFoundException {
//		FlatAddress flatAddress = new FlatAddress(1, "street", "city", "state", 600001, null);
//		Tenant tenant = new Tenant(1, "alpha", 24, flatAddress);
//		try
//		{
//			assertNull(service.addTenant(tenant));
//		}
//		catch(TenantNotFoundException exception)
//		{
//			assertEquals("Country cannot be empty", exception.getMessage());
//		}
//	}


	
//	@Test
//	void testUpdateTenant() throws TenantNotFoundException {
//		FlatAddress flatad = new FlatAddress(21, "gandhi", "cbe", "tn", 64367, "India");
//		Tenant tenant = new Tenant(2, "alpha", 20, flatad);
//		service.addTenant(tenant);
//		tenant.setTenantAge(22);
//		service.updateTenant(tenant);
//		assertEquals(22,tenant.getTenantAge());
//		verify(service,times(1)).updateTenant(tenant);
//	}
//
//	@Test
//	void testDeleteTenant() throws TenantNotFoundException {
//		FlatAddress flatad = new FlatAddress(31, "vishnust", "che", "hy", 64289, "JP");
//		Tenant tenant = new Tenant(3, "alpha", 30, flatad);
//		service.deleteTenant(tenant.getTenantId());
//		verify(service,times(1)).deleteTenant(tenant.getTenantId());
//		assertNotNull(service);
//	}

//	@Test
//	void testViewTenant() throws TenantNotFoundException {
//		FlatAddress flatad = new FlatAddress(41, "sat", "mad", "ban", 64343, "AP");
//		Tenant tenant = new Tenant(4, 40, flatad);
//		service.addTenant(tenant);
//		Optional<Tenant> findById = Optional.ofNullable(tenant);
//		when(service.viewTenant(4)).thenReturn(findById);
//		Optional<Tenant> findById2 = service.viewTenant(4);
//		assertEquals(findById, findById2);
//	}

//	@Test
//	void testViewAllTenant() {
//		List<Tenant> tenantList = new ArrayList<Tenant>();
//		FlatAddress flatad = new FlatAddress(51, "hema", "pollachi", "che", 64222, "UP");
//		Tenant tenant = new Tenant(5, "alpha", 50, flatad);
//		tenantList.add(tenant);
//		when(service.viewAllTenant()).thenReturn(tenantList);
//		List<Tenant> tenantList1 = service.viewAllTenant();
//		assertEquals(1, tenantList1.size());
//		verify(service,times(1)).viewAllTenant();
//		
//	}

}
