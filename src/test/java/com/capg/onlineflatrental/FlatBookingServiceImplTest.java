package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.service.IFlatBookingService;

@SpringBootTest
public class FlatBookingServiceImplTest {
	@Autowired
	IFlatBookingService service;
	
	FlatBooking flatbooking= null;
	FlatAddress flatAddress = null;
	Flat flat = null;
	Tenant tenant = null;
	
	@BeforeAll
	public static void init() {
		//System.out.println("Before All Executed");
	}

//	@Test
//	void testAddFlatBooking01() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
//		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
//		flat = new Flat(1, 1000f, flatAddress, "yes");
//		tenant = new Tenant(1, "name", 40, flatAddress);
//		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
//		assertNotNull(service.addFlatBooking(flatbooking));
//	}
	
	@Test
	void testAddFlatBooking02() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "name", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, null, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant details cannot be blank", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking03() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "a", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking04() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "AlphaAlphaAlphaAlphaAlphaAlphaAlphaAlpha", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking05() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "Alpha123", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking06() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "Alpha123@", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking07() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "13", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking08() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "14", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking09() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "0", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking10() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "0", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking12() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "0", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("House Number cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking13() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "0", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Street cannot be empty", exception.getMessage());
		}
	}
	
	
	@Test
	void testAddFlatBooking14() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("City cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking15() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("State cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking16() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "0", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking17() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Country cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking18() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "chennai135", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking19() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "chennai123$", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
//	@Test
//	void testAddFlatBooking20() throws FlatBookingNotFoundException {
//		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
//		flat = new Flat(1, 1000f, flatAddress, "yes");
//		tenant = new Tenant(1, "0", 40, flatAddress);
//		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
//		try
//		{
//			service.addFlatBooking(FlatBooking);
//		}
//		catch(FlatBookingNotFoundException exception)
//		{
//			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
//		}
//	}
	
	@Test
	void testAddFlatBooking21() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "tamilnadu456", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking22() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "123#", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking23() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "123%", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking26() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "0", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking27() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "12346789", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
	
	@Test
	void testAddFlatBooking28() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.addFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
	
//	@Test
//	void testUpdateFlatBooking01() throws FlatBookingNotFoundException {
//		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
//		flatbooking = new FlatBooking(210, "Alpha", 40, flatAddress);
//		assertNotNull(service.updateFlatBooking(flatbooking));
//	}
//	
	@Test
	void testUpdatev02() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking04() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "basheer", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant name cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking05() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking06() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking07() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking08() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking09() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking10() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking11() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking12() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(TenantNotFoundException exception)
		{
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking14() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("House Number cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking15() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Street cannot be empty", exception.getMessage());
		}
	}
	
	
	@Test
	void testUpdateFlatBooking16() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("City cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking17() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("State cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking18() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking19() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Country cannot be empty", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking20() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking21() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking22() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
//	@Test
//	void testUpdateFlatBooking23() throws FlatBookingNotFoundException {
//		flatAddress = new FlatAddress(1, "street", "city", "Tamil_Nadu", 600001, "country");
//		flatbooking = new FlatBooking(210, "Alpha", 40, flatAddress);
//		try
//		{
//			service.updateFlatBooking(flatboooking);
//		}
//		catch(FlatBookingNotFoundException exception)
//		{
//			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
//		}
//	}
//	
	@Test
	void testUpdateFlatBooking24() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking25() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking28() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking29() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
	
	@Test
	void testUpdateFlatBooking30() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try
		{
			service.updateFlatBooking(flatbooking);
		}
		catch(FlatBookingNotFoundException exception)
		{
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}
//	@Test
//	void testDeleteFlatBooking01() throws FlatBookingNotFoundException {
//		assertEquals(service.viewFlatBooking(219).getFlatBookingName(), service.deleteFlatBooking(219).getFlatBookingName());
//	}
//	
//	@Test
//	void testDeleteFlatBooking02() throws FlatBookingNotFoundException {
//		try
//		{
//			service.deleteFlatBooking(1);
//		}
//		catch(FlatBookingNotFoundException exception)
//		{
//			assertEquals("No Tenant found in given ID", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testDeleteFlatBooking03() throws FlatBookingNotFoundException {
//		try
//		{
//			service.deleteFlatBooking(0);
//		}
//		catch(FlatBookingNotFoundException exception)
//		{
//			assertEquals("No Tenant found in given ID", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testViewFlatBooking() throws FlatBookingNotFoundException {
//		assertEquals("Alpha", service.viewFlatBooking(210).getFlatBookingName());
//	}
//	
//	@Test
//	void testViewFlatBooking02() throws FlatBookingNotFoundException {
//		try
//		{
//			service.deleteFlatBooking(1);
//		}
//		catch(FlatBookingNotFoundException exception)
//		{
//			assertEquals("No Tenant found in given ID", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testViewFlatBooking03() throws FlatBookingNotFoundException {
//		try
//		{
//			service.deleteFlatBooking(0);
//		}
//		catch(FlatBookingNotFoundException exception)
//		{
//			assertEquals("No Tenant found in given ID", exception.getMessage());
//		}
//	}
//
//	@Test
//	void testViewAllFlatBooking01() {
//		assertNotNull(service.viewAllFlatBooking());
//	}
//	
//	@Test
//	void testViewAllFlatBooking02() throws FlatBookingNotFoundException{
//		try
//		{
//			assertNull(service.viewAllFlatBooking());
//		}
//		catch (AssertionFailedError exception)
//		{
//			assertNotNull(service.viewAllFlatBooking());
//		}
//	}

}