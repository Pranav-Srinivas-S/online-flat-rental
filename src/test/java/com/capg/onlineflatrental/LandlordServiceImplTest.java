package com.capg.onlineflatrental;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.service.ILandlordService;

@SpringBootTest
class LandlordServiceImplTest {

	@Autowired
	ILandlordService service;
	
	Landlord landlord = null;
	List<Flat> flatList =new ArrayList<Flat>();
	FlatAddress flatAddress=null;
	
	@BeforeAll
	public static void init() {
		
	}

	@Test
	void testAddLandlord01() throws LandlordNotFoundException {
//		flatAddress=new FlatAddress(1,"street","city","state",000013,"country");
//		
//	    flatList(1,1000f,flatAddress,"Y");
//		landlord = new Landlord(1, "Name", 24,flatList);
//		assertNotNull(service.addLandlord(landlord));
//	}
//
//	@Test
//	void testAddLandlord02() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(2, null, 25, );
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Landlord name cannot be empty", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testAddLandlord03() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(3, "ni", 26,);
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Landlord Name length should be in range 3 to 30", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testAddLandlord04() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(4, "", 40, );
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Landlord Name length should be in range 3 to 30", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testAddLandlord05() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(5, "Name123", 24,);
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Landlord Name cannot contain Numbers or Special Characters", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testAddLandlord06() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(6, "Name#$", 25, );
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Landlord Name cannot contain Numbers or Special Characters", exception.getMessage());
//		}
//	}
//	
//	
//	@Test
//	void testAddLandlord07() throws LandlordNotFoundException {
//		landlord = new Landlord(7, "Name", 17, );
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Minor Age is not allowed", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testAddLandlord08() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(8, "Name", 0, );
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Age cannot be 0 or negative", exception.getMessage());
//		}
//	}
//	
//	@Test
//	void testAddLandlord09() throws LandlordNotFoundException {
//		
//		landlord = new Landlord(9, "Name", -1,);
//		try
//		{
//			service.addLandlord(landlord);
//		}
//		catch(LandlordNotFoundException exception)
//		{
//			assertEquals("Age cannot be 0 or negative", exception.getMessage());
//		}
//	}
//	
}}