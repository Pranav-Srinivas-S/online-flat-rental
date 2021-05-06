package com.capg.onlineflatrental.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;
import com.capg.onlineflatrental.repository.ILandlordRepository;
import com.capg.onlineflatrental.util.LandlordUtils;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Landlord Service Layer
*/

@Service
public class LandlordServiceImpl implements ILandlordService {

	static final Logger LOGGER = LoggerFactory.getLogger(LandlordServiceImpl.class);

	@Autowired
	private ILandlordRepository landlordRepo;

	static String landlordNotFound = "No Landlord found in given ID";
	
	static String validationSuccessful = "Validation Successful";

	/*
	 * Description : This method Adds new Landlord
	 * Input Param : Landlord Object 
	 * Return Value : LandlordDTO Object 
	 * Exception : LandlordNotFoundException, InvalidFlatInputException
	 */
	@Override
	public LandlordDTO addLandlord(Landlord landlord) throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("addLandlord() service is initiated");
		Landlord landlordEntity = null;
		validateLandlord(landlord);
		landlordEntity = landlordRepo.save(landlord);
		LOGGER.info("addLandlord() service has executed");
		return LandlordUtils.convertToLandlordDto(landlordEntity);
	}

	/*
	 * Description : This method Updates existing Landlord
	 * Input Param : Landlord Object 
	 * Return Value : LandlordDTO Object 
	 * Exception : LandlordNotFoundException, InvalidFlatInputException
	 */
	@Override
	public LandlordDTO updateLandlord(Landlord landlord) throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("updateLandlord() service is initiated");
		Landlord landlordEntity;
		Landlord existLandlord = landlordRepo.findById(landlord.getLandlordId()).orElse(null);
//		if (existLandlord == null)
//			throw new LandlordNotFoundException(landlordNotFound);
//		else
//		{
//			validateLandlord(landlord);
			landlordEntity = landlordRepo.save(landlord);
		
		LOGGER.info("updateLandlord() service has executed");
		return LandlordUtils.convertToLandlordDto(landlordEntity);
	}

	/*
	 * Description : This method Deletes existing Landlord
	 * Input Param : int
	 * Return Value : LandlordDTO Object 
	 * Exception : LandlordNotFoundException
	 */
	@Override
	public LandlordDTO deleteLandlord(int id) throws LandlordNotFoundException {
		LOGGER.info("deleteLandlord() service is initiated");
		Landlord existLandlord = landlordRepo.findById(id).orElse(null);
		if (existLandlord == null)
			throw new LandlordNotFoundException(landlordNotFound);
		else
			landlordRepo.delete(existLandlord);
		LOGGER.info("deleteLandlord() service has executed");
		return LandlordUtils.convertToLandlordDto(existLandlord);
	}

	/*
	 * Description : This method Shows existing Landlord
	 * Input Param : int
	 * Return Value : LandlordDTO Object 
	 * Exception : LandlordNotFoundException
	 */
	@Override
	public LandlordDTO viewLandlord(int id) throws LandlordNotFoundException {
		LOGGER.info("viewLandlord() service is initiated");
		Landlord existLandlord = landlordRepo.findById(id).orElse(null);
		if (existLandlord == null)
			throw new LandlordNotFoundException(landlordNotFound);
		LOGGER.info("viewLandlord() service has executed");
		return LandlordUtils.convertToLandlordDto(existLandlord);
	}

	/*
	 * Description : This method Shows all Landlords
	 * Return Value : List<LandlordDTO> Object 
	 */
	@Override
	public List<LandlordDTO> viewAllLandlord() {
		LOGGER.info("viewAllLandlord() service is initiated");
		List<Landlord> landlordList = landlordRepo.findAll();
		LOGGER.info("viewAllLandlord() service has executed");
		return LandlordUtils.convertToLandlordDtoList(landlordList);

	}

	public static boolean validateLandlord(Landlord landlord)
			throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("validateLandlord() is initiated");
		boolean flag = false;
		if (landlord == null) {
			LOGGER.error("Landlord details cannot be blank");
			throw new LandlordNotFoundException("Landlord details cannot be blank");
		}
		else {
			validateLandlordAge(landlord.getLandlordAge());
			validateLandlordName(landlord.getLandlordName());
			FlatServiceImpl.validateFlat(landlord.getFlatList());
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateLandlord() has executed");
		return flag;
	}

	
	public static boolean validateLandlordName(String landlordName) throws LandlordNotFoundException {
		LOGGER.info("validateLandlordName() is initiated");
		boolean flag = false;
		if (landlordName == null) {
			LOGGER.error("Landlord name cannot be empty");
			throw new LandlordNotFoundException("Landlord name cannot be empty");
		} else if (!landlordName.matches("^[A-Za-z ]+$")) {
			LOGGER.error("Landlord name cannot contain Numbers or Special Characters");
			throw new LandlordNotFoundException("Landlord name cannot contain Numbers or Special Characters");
		} else if (landlordName.length() < 3 || landlordName.length() > 30) {
			LOGGER.error("Landlord name length should be in range 3 to 30");
			throw new LandlordNotFoundException("Landlord name length should be in range 3 to 30");
		} else {
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateLandlordName() has executed");
		return flag;
	}

	public static boolean validateLandlordAge(int age) throws LandlordNotFoundException {
		LOGGER.info("validateLandlordAge() is initiated");
		boolean flag = false;
		if (age <= 0) {
			LOGGER.error("Age cannot be 0 or negative");
			throw new LandlordNotFoundException("Age cannot be 0 or negative");
		} else if (age < 18) {
			LOGGER.error("Minor Age is not allowed");
			throw new LandlordNotFoundException("Minor Age is not allowed");
		} else {
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateLandlordAge() has executed");
		return flag;
	}

}
