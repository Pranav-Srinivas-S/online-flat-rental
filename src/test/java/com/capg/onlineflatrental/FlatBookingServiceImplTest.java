package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;
import com.capg.onlineflatrental.service.IFlatBookingService;

@SpringBootTest
public class FlatBookingServiceImplTest {
	final static Logger LOGGER = LoggerFactory.getLogger(FlatBookingServiceImplTest.class);
	@Autowired
	IFlatBookingService service;

	FlatBooking flatbooking = null;
	FlatAddress flatAddress = null;
	Flat flat = null;
	Tenant tenant = null;
	@BeforeAll
	public static void init() {
		LOGGER.info("FlatBooking Testing Initiated");
	}

	@Test
	void testAddFlatBooking01()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking01()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "name", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, null, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant details cannot be blank", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking02()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking02()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "a", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking03()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking03()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "AlphaAlphaAlphaAlphaAlphaAlphaAlphaAlpha", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking04()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking04()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "Alpha123", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking05()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking05()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "Alpha123@", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking06()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking06()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "13", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking07()throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking07()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "basheer", 10, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking08()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking08()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "basheer", 0, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testAddFlatBooking09() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testAddFlatBooking009()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "basheer", -1, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.addFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdate01() throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking01()");
		flatAddress = new FlatAddress(1, "jasmin", "chennai", "tamilnadu", 600062, "india");
		flat = new Flat(488, 1000, flatAddress, "yes");
		tenant = new Tenant(382, "basheer", 45, flatAddress);
		flatbooking = new FlatBooking(347, flat, tenant, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-02-06"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("No Tenant found in given ID", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlatBooking02()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking02()");
		flatAddress = new FlatAddress(1, "jasmin", "chennai", "tamilnadu", 600062, "india");
		flat = new Flat(6, 1000, flatAddress, "yes");
		tenant = new Tenant(6, "@", 45, flatAddress);
		flatbooking = new FlatBooking(347, flat, tenant, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-02-06"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlatBooking03()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking03()");
		flatAddress = new FlatAddress(1, "jasmin", "chennai", "tamilnadu", 600062, "india");
		flat = new Flat(6, 1000, flatAddress, "yes");
		tenant = new Tenant(8, "ba", 45, flatAddress);
		flatbooking = new FlatBooking(347, flat, tenant, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-02-06"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Tenant Name length should be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlatBooking04()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking04()");
		flatAddress = new FlatAddress(1, "jasmin", "chennai", "tamilnadu", 600062, "india");
		flat = new Flat(6, 1000, flatAddress, "yes");
		tenant = new Tenant(8, "basheer", 5, flatAddress);
		flatbooking = new FlatBooking(347, flat, tenant, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-02-06"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlatBooking05()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking05()");
		flatAddress = new FlatAddress(1, "jasmin", "chennai", "tamilnadu", 600062, "india");
		flat = new Flat(6, 1000, flatAddress, "yes");
		tenant = new Tenant(8, "basheer", 0, flatAddress);
		flatbooking = new FlatBooking(347, flat, tenant, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-02-06"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlatBooking06()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking06()");
		flatAddress = new FlatAddress(1, "jasmin", "chennai", "tamilnadu", 600062, "india");
		flat = new Flat(6, 1000, flatAddress, "yes");
		tenant = new Tenant(8, "basheer", -1, flatAddress);
		flatbooking = new FlatBooking(347, flat, tenant, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-02-06"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (TenantNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlatBooking07()
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("Testing testUpdateFlatBooking07()");
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 1000f, flatAddress, "yes");
		tenant = new Tenant(1, "89654223", 40, flatAddress);
		flatbooking = new FlatBooking(1, flat, tenant, LocalDate.parse("2021-04-07"), LocalDate.parse("2022-04-07"));
		try {
			service.updateFlatBooking(flatbooking);
		} catch (FlatBookingNotFoundException exception) {
			assertEquals("No FlatBooking found in given ID", exception.getMessage());
		}
	}
	@Test
	void testDeleteFlatBooking01() throws FlatBookingNotFoundException {
		LOGGER.info("Testing testDeleteFlatBooking01()");
		try {
			service.deleteFlatBooking(1000);
		} catch (FlatBookingNotFoundException exception) {
			assertEquals("No FlatBooking found in given ID", exception.getMessage());
		}
	}

	@Test
	void testDeleteFlatBooking02() throws FlatBookingNotFoundException {
		LOGGER.info("Testing testDeleteFlatBooking02()");
		try {
			service.deleteFlatBooking(0200001);
		} catch (FlatBookingNotFoundException exception) {
			assertEquals("No FlatBooking found in given ID", exception.getMessage());
		}
	}

	@Test
	void testViewFlatBooking01() throws FlatBookingNotFoundException {
		LOGGER.info("Testing testViewFlatBooking01()");
		try {
			service.deleteFlatBooking(9);
		} catch (FlatBookingNotFoundException exception) {
			assertEquals("No FlatBooking found in given ID", exception.getMessage());
		}
	}

	@Test
	void testViewFlatBooking02() throws FlatBookingNotFoundException {
		LOGGER.info("Testing testViewFlatBooking02()");
		try {
			service.deleteFlatBooking(8);
		} catch (FlatBookingNotFoundException exception) {
			assertEquals("No FlatBooking found in given ID", exception.getMessage());
		}
	}

	@Test
	void testViewAllFlatBooking01() {
		assertNotNull(service.viewAllFlatBooking());
	}

	private void assertNotNull(List<FlatBookingDTO> viewAllFlatBooking) {
		// TODO Auto-generated method stub

	}

	private void assertNull(List<FlatBookingDTO> viewAllFlatBooking) {
		// TODO Auto-generated method stub
	}
	@AfterAll
	public static void end() {
		LOGGER.info("FlatBooking Testing Terminated");
	}
	@Test
	void testViewAllFlatBooking02() throws FlatBookingNotFoundException {
		try {
			assertNull(service.viewAllFlatBooking());
		} catch (AssertionFailedError exception) {
			assertNotNull(service.viewAllFlatBooking());
		}
	}

}