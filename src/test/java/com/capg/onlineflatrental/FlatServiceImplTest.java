package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.exception.FlatNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.model.FlatDTO;
import com.capg.onlineflatrental.service.IFlatService;

@SpringBootTest
class FlatServiceImplTest {
	
	final static Logger LOGGER = LoggerFactory.getLogger(FlatServiceImplTest.class);

	@Autowired
	IFlatService service;

	Flat flat = null;
	FlatAddress flatAddress = null;
	
	@BeforeAll
	public static void beforeAll() {
		LOGGER.info("Flat Testing started");
	}

	@Test
	void testAddFlat01() throws FlatNotFoundException, InvalidFlatInputException {
		FlatAddress address = new FlatAddress(10, "Street", "City", "State", 876534, "India");
		Flat flat = new Flat(26, 1200, address, "Yes");
		FlatDTO dto = (service.addFlat(flat));
		assertNotNull(dto);
	}

	@Test
	void testAddFlat02() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456, flatAddress, "");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availability cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddFlat03() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456, flatAddress, "Available");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availabilty can only be [YES | NO | Yes | No | Y | N | y | n]", exception.getMessage());
		}
	}

	@Test
	void testAddFlat04() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 0, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Cost cannot be 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testAddFlat05() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Cost cannot be 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testAddFlat06() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(0, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("HouseNo cannot be empty or 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testAddFlat07() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(-11, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("HouseNo cannot be empty or 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testAddFlat08() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Street name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddFlat09() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "@street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Street cannot contain Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlat10() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("City name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddFlat11() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "&Chennai", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlat12() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "@street", "&Chennai", null, 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Street cannot contain Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlat13() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "@Kerala", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlat14() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600001, "");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Country name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testAddFlat15() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600001, "#India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testAddFlat16() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 0, "India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testAddFlat17() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600004, "India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testAddFlat18() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600, "India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat01() throws FlatNotFoundException, InvalidFlatInputException {
		FlatAddress address = new FlatAddress(10, "Street", "City", "Tamil Nadu", 876534, "India");
		Flat flat = new Flat(26, 1200, address, "Yes");
		try {
			FlatDTO dto = (service.updateFlat(flat));
			assertEquals("Tamil Nadu", dto.getFlatAddress().getState());
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat02() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456, flatAddress, "");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		}

		catch (InvalidFlatInputException exception) {
			assertEquals("Availability cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat03() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456, flatAddress, "Available");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availabilty can only be [YES | NO | Yes | No | Y | N | y | n]", exception.getMessage());
		}
	}

	@Test
	void testUpdate04() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 0, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Cost cannot be 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testUpdate05() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Cost cannot be 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testUpdate06() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(0, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("HouseNo cannot be empty or 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testUpdate07() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(-11, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("HouseNo cannot be empty or 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testUpdate08() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Street name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testUpdate09() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "@street", "city", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Street cannot contain Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdate10() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("City name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testUpdate11() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "&Chennai", "state", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat12() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "@street", "&Chennai", null, 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Street cannot contain Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat13() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "@Kerala", 600001, "country");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat14() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600001, "");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Country name cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat15() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600001, "#India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat16() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 0, "India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat17() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600004, "India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("PinCode cannot be 0 or negative", exception.getMessage());
		}
	}

	@Test
	void testUpdateFlat18() throws InvalidFlatInputException, FlatNotFoundException {
		flatAddress = new FlatAddress(11, "street", "Chennai", "Kerala", 600, "India");
		flat = new Flat(1, 4500, flatAddress, "Yes");
		try {
			service.updateFlat(flat);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		} catch (InvalidFlatInputException exception) {
			assertEquals("PinCode should be length 6", exception.getMessage());
		}
	}

	@Test
	void testDeleteFlat() throws FlatNotFoundException {
		try {
			assertNull(service.deleteFlat(26));
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		}
	}

	@Test
	void testViewFlat01() throws FlatNotFoundException {
		try {
			assertEquals(1200, service.viewFlat(26).getFlatCost());
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		}
	}

	@Test
	void testViewFlat02() throws FlatNotFoundException {
		try {
			assertEquals("India", service.viewFlat(26).getFlatAddress().getCountry());
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		}
	}

	@Test
	void testViewAllFlat() {
		try {
			assertNull(service.viewAllFlat());
		} catch (AssertionFailedError exception) {
			assertNotNull(service.viewAllFlat());
		}

	}

	@Test
	void testViewAllFlatByCost01() throws InvalidFlatInputException, FlatNotFoundException {

		try {
			service.viewAllFlatByCost(0, "Yes");
		} catch (InvalidFlatInputException exception) {
			assertEquals("Cost cannot be 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testViewAllFlatByCost02() throws InvalidFlatInputException, FlatNotFoundException {

		try {
			service.viewAllFlatByCost(-1200, "Yes");
		} catch (InvalidFlatInputException exception) {
			assertEquals("Cost cannot be 0 or a negative number", exception.getMessage());
		}
	}

	@Test
	void testViewAllFlatByCost03() throws InvalidFlatInputException, FlatNotFoundException {

		try {
			service.viewAllFlatByCost(3200, "available");
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availabilty can only be [YES | NO | Yes | No | Y | N | y | n]", exception.getMessage());
		}
	}

	@Test
	void testViewAllFlatByCost04() throws InvalidFlatInputException, FlatNotFoundException {

		try {
			service.viewAllFlatByCost(3200, "");
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availability cannot be empty", exception.getMessage());
		}
	}

	@Test
	void testViewAllFlatByCost05() throws InvalidFlatInputException, FlatNotFoundException {

		try {

			assertNotNull(service.viewAllFlatByCost(3200, "Yes"));
		} catch (FlatNotFoundException exception) {
			assertEquals("No flat available for given cost", exception.getMessage());
		}
	}
	
	@AfterAll
	public static void end() {
		LOGGER.info("Flat Testing Finished");
	}


}
