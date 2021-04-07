package com.capg.onlineflatrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;
import com.capg.onlineflatrental.repository.ILandlordRepository;
import com.capg.onlineflatrental.util.LandlordUtils;

@Service
public class LandlordServiceImpl implements ILandlordService {
	@Autowired
	ILandlordRepository landlordRepo;
	
	static String landlordNotFound = "No Landlord found in given ID";
	

	@Override
	public LandlordDTO addLandlord(Landlord landlord) throws LandlordNotFoundException {
		Landlord landlordEntity;
		if(landlord == null)
			landlordEntity = null;
		else
			landlordEntity = landlordRepo.save(landlord);
		return LandlordUtils.convertToLandlordDto(landlordEntity);
	}

	@Override
	public LandlordDTO updateLandlord(Landlord landlord) throws LandlordNotFoundException {
		
		Landlord landlordEntity;
		if(landlord == null)
			landlordEntity = null;
	    Landlord existLandlord = landlordRepo.findById(landlord.getLandlordId()).orElse(null);
		if(existLandlord == null)
			throw new LandlordNotFoundException(landlordNotFound);
		else
			landlordEntity = landlordRepo.save(landlord);
		return LandlordUtils.convertToLandlordDto(landlordEntity);
	}

	@Override
	public LandlordDTO deleteLandlord(int id) throws LandlordNotFoundException {
		Landlord existLandlord =landlordRepo.findById(id).orElse(null);
		if(existLandlord == null)
			throw new LandlordNotFoundException(landlordNotFound);
		else
			landlordRepo.delete(existLandlord);
		return LandlordUtils.convertToLandlordDto(existLandlord);
	}
	@Override
	public LandlordDTO viewLandlord(int id) throws LandlordNotFoundException {
		Landlord existLandlord = landlordRepo.findById(id).orElse(null);
		if(existLandlord == null)
			throw new LandlordNotFoundException(landlordNotFound);
		return LandlordUtils.convertToLandlordDto(existLandlord);
	}

	@Override
	public List<LandlordDTO> viewAllLandlord() {
		List<Landlord> LandlordList=landlordRepo.findAll();
		return LandlordUtils.convertToLandlordDtoList(LandlordList);
		
	}
	public boolean validateLandlordId(int id) throws LandlordNotFoundException
	{
		boolean flag = landlordRepo.existsById(id);
		if(flag == false)
			throw new LandlordNotFoundException(landlordNotFound);
		return flag;
	}
	
	public static boolean validateLandlord(Landlord landlord) throws LandlordNotFoundException, InvalidFlatInputException
	{
		boolean flag = false;
		if(landlord == null)
			throw new LandlordNotFoundException("Landlord details cannot be blank");
		else if(!(validateLandlordAge(landlord.getLandlordAge())))
			throw new LandlordNotFoundException("Invalid Age");
		else if(!(validateLandlordName(landlord.getLandlordName())))
			throw new LandlordNotFoundException("Invalid Name");
		else if(!validateLandlordFlat(landlord.getFlatList()))
			throw new LandlordNotFoundException("Invalid flat Details");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateLandlordFlat(List<Flat> flatList) throws LandlordNotFoundException, InvalidFlatInputException
	{
		boolean flag = false;
		for (Flat flat : flatList)
		{
			if(!FlatServiceImpl.validateFlat(flat))
				throw new LandlordNotFoundException("Invalid Flat Details");
			else
				flag = true;
		}
		return flag;
	}
	

	public static boolean validateLandlordName(String landlordName) throws LandlordNotFoundException 
	{
		boolean flag = false;
		if(landlordName == null)
			throw new LandlordNotFoundException("Landlord name cannot be empty");
		else if(!landlordName.matches("^[A-Za-z ]+$"))
			throw new LandlordNotFoundException("Landlord name cannot contain Numbers or Special Characters");
		else if(landlordName.length()<3 || landlordName.length()>30)
			throw new LandlordNotFoundException("Landlord name length should be in range 3 to 30");
		else
			flag = true;
		return flag;
	}

	public static boolean validateLandlordAge(int age) throws LandlordNotFoundException
	{
		boolean flag = false;
		if(age <= 0)
			throw new LandlordNotFoundException("Age cannot be 0 or negative");
		else if(age < 18)
			throw new LandlordNotFoundException("Minor Age is not allowed");
		else
			flag = true;
		return flag;
	}
	
//	public static boolean validateLandlordHouseNo(int houseNo) throws LandlordNotFoundException
//	{
//		boolean flag = false;
//		if(houseNo <= 0)
//			throw new LandlordNotFoundException("House Number cannot be 0 or negative");
//		else
//			flag = true;
//		return flag;
//	}
//	
//	public static boolean validateLandlordStreet(String street) throws LandlordNotFoundException
//	{
//		boolean flag = false;
//		if(street == null)
//			throw new LandlordNotFoundException("Street cannot be empty");
//		else if(!street.matches("^[a-zA-Z]+$"))
//			throw new LandlordNotFoundException("Street cannot contain Numbers or Special Characters");
//		else
//			flag = true;
//		return flag;
//	}
//
//	public static boolean validateLandlordCity(String city) throws LandlordNotFoundException
//	{
//		boolean flag = false;
//		if(city == null)
//			throw new LandlordNotFoundException("city cannot be empty");
//		else if(!city.matches("^[a-zA-Z]+$"))
//			throw new LandlordNotFoundException("City cannot contain Numbers or Special Characters");
//		else
//			flag = true;
//		return flag;
//	}
//
//	public static boolean validateLandlordState(String state) throws LandlordNotFoundException
//	{
//		boolean flag = false;
//		if(state == null)
//			throw new LandlordNotFoundException("State cannot be empty");
//		else if(!state.matches("^[a-zA-Z a-zA-Z]+$"))
//			throw new LandlordNotFoundException("State cannot contain Numbers or Special Characters");
//		else
//			flag = true;
//		return flag;
//	}
//
//	public static boolean validateLandlordCountry(String country) throws LandlordNotFoundException
//	{
//		boolean flag = false;
//		if(country == null)
//			throw new LandlordNotFoundException("Country cannot be empty");
//		else if(!country.matches("^[a-zA-Z]+$"))
//			throw new LandlordNotFoundException("Country cannot contain Numbers or Special Characters");
//		else
//			flag = true;
//		return flag;
//	}
//	
//	public static boolean validateLandlordPin(long pin) throws LandlordNotFoundException
//	{
//		boolean flag = false;
//		if(pin <= 0)
//			throw new LandlordNotFoundException("PinCode cannot be 0 or negative");
//		else if(Long.toString(pin).length() != 6)
//			throw new LandlordNotFoundException("PinCode should be length 6");
//		else if(!Long.toString(pin).matches("^[0-9]+$"))
//			throw new LandlordNotFoundException("PinCode cannot contain any Characters");
//		else
//			flag = true;
//		return flag;
//	}
	
}
