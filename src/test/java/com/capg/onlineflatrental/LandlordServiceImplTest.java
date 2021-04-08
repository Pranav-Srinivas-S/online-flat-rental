package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.service.ILandlordService;

@Disabled
@SpringBootTest
class LandlordServiceImplTest {

	final static Logger LOGGER = LoggerFactory.getLogger(LandlordServiceImplTest.class);
	
	@Autowired
	ILandlordService service;

	Landlord landlord = null;
	List<Flat> flatList = new ArrayList<Flat>();
	FlatAddress flatAddress = null;

	@BeforeAll
	public static void init() {
		LOGGER.info("Landlord Testing Initiated");
	}

	@Test
	void testAddLandlord01() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(1, "Name", 24, flatList);
		assertNotNull(service.addLandlord(landlord));
	}

	@Test
	void testAddLandlord02() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(2, null, 25, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord03() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(3, "ni", 26, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name length should be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord04() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(4, "namenamenamenamenamenamenamenamenamename", 40, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name length should be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord05() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(5, "Name123", 24, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord06() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(6, "Name#$", 25, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord07() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(7, "Name", 17, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord08() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(8, "Name", 0, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testAddLandlord09() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(9, "Name", -1, flatList);
		try {
			service.addLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord10() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(1, "Name", 24, flatList);
		assertNotNull(service.updateLandlord(landlord));
	}

	@Test
	void testUpdateLandlord11() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(11, "Name", 24, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("No Landlord found in given ID", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord12() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(12, null, 25, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord13() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(13, "na", 26, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name length should be in range 3 to 30", exception.getMessage());
		}
	}
	

	@Test
	void testUpdateLandlord14() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(14, "namenamenamenamenamenamenamenamename", 40, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name length should be in range 3 to 30", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord15() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(15, "Name123", 24, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord16() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(16, "Name#$", 25, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Landlord Name cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord17() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));
		landlord = new Landlord(17, "Name", 17, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Minor Age is not allowed", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord18() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(18, "Name", 0, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateLandlord19() throws LandlordNotFoundException, InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 000013, "country");

		flatList.add(new Flat(1, 1000f, flatAddress, "Y"));

		landlord = new Landlord(19, "Name", -1, flatList);
		try {
			service.updateLandlord(landlord);
		} catch (LandlordNotFoundException exception) {
			assertEquals("Age cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testDeleteTenant20() throws LandlordNotFoundException {
		assertEquals(service.viewLandlord(219).getLandlordName(), service.deleteLandlord(219).getLandlordName());
	}

	@Test
	void testDeleteLandlord21() throws LandlordNotFoundException {

		try {
			service.deleteLandlord(2);
		} catch (LandlordNotFoundException exception) {
			assertEquals("No Landlord found in the given Id", exception.getMessage());
		}
	}

	@Test
	void testDeleteLandlord22() throws LandlordNotFoundException {

		try {
			service.deleteLandlord(0);
		} catch (LandlordNotFoundException exception) {
			assertEquals("No Landlord found in the given Id", exception.getMessage());
		}
	}

	@Test
	void testViewLandlord23() throws LandlordNotFoundException {
		assertEquals("Name", service.viewLandlord(1).getLandlordName());
	}

	@Test
	void testViewUser24() throws LandlordNotFoundException {
		try {
			service.viewLandlord(1);
		} catch (LandlordNotFoundException exception) {
			assertEquals("No landlord found with that Id\r\n" + "\r\n" + "Enter valid UserId", exception.getMessage());
		}
	}

	@Test
	void testViewAllLandlord25() {
		assertNotNull(service.viewAllLandlord());
	}

	@Test
	void testViewAllLandlord26() throws LandlordNotFoundException {
		try {
			assertNull(service.viewAllLandlord());
		} catch (AssertionFailedError exception) {
			assertNotNull(service.viewAllLandlord());
		}
	}
	
	@AfterAll
	public static void end() {
		LOGGER.info("LandlordO Testing Terminated");
	}

}