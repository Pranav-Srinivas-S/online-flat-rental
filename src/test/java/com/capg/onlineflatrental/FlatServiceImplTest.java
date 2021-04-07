package com.capg.onlineflatrental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.exception.FlatNotFoundException;
import com.capg.onlineflatrental.model.FlatDTO;
import com.capg.onlineflatrental.service.FlatServiceImpl;
import com.capg.onlineflatrental.service.IFlatService;

@SpringBootTest
class FlatServiceImplTest {

	@Autowired
	IFlatService service;

	@Test
	void testAddFlat() throws FlatNotFoundException {
		FlatAddress address = new FlatAddress(10, "Street", "City", "State", 876534, "India");
		Flat flat = new Flat(26, 1200, address, "Yes");
		FlatDTO dto = (service.addFlat(flat));
		assertNotNull(dto);
	}

	@Test
	void testUpdateFlat() throws FlatNotFoundException {
		FlatAddress address = new FlatAddress(10, "Street", "City", "Tamil Nadu", 876534, "India");
		Flat flat = new Flat(26, 1200, address, "Yes");
		FlatDTO dto = (service.addFlat(flat));
		assertEquals("Tamil Nadu", dto.getFlatAddress().getState());
	}

	@Test
	void testDeleteFlat() throws FlatNotFoundException {
		try {
			FlatDTO dto = (service.deleteFlat(26));
			assertNull(dto);
		} catch (FlatNotFoundException exception) {
			assertEquals("Flat with given id was not found", exception.getMessage());
		}
	}

	/*
	 * @Test void testViewFlat() { }
	 * 
	 * @Test void testViewAllFlat() { fail("Not yet implemented"); }
	 * 
	 * @Test void testViewAllFlatByCost() { fail("Not yet implemented"); }
	 */
	 

}
