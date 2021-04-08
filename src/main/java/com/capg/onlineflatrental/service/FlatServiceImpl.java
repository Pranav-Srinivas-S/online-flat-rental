package com.capg.onlineflatrental.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.exception.FlatNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.model.FlatDTO;
import com.capg.onlineflatrental.repository.IFlatRepository;
import com.capg.onlineflatrental.util.FlatUtils;

/*
 * Author : AJITHKUMAR A 
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Flat Service Layer
*/

@Service
public class FlatServiceImpl implements IFlatService {

	final static Logger LOGGER = LoggerFactory.getLogger(FlatServiceImpl.class);

	@Autowired
	private IFlatRepository flatRepo;

	/*
	 * Description : This method Adds new Flat 
	 * Input Param : Flat Object 
	 * Return Value : FlatDTO Object 
	 * Exception : InvalidFlatInputException
	 */
	@Override
	public FlatDTO addFlat(Flat flat) throws InvalidFlatInputException {
		LOGGER.info("addFlat() service is initiated");
		Flat flatEntity;
		if (flat == null)
			flatEntity = null;
		else if (!validateFlat(flat))
			throw new InvalidFlatInputException("Invalid inputs for adding flat details");
		else
			flatEntity = flatRepo.save(flat);
		LOGGER.info("addFlat() service has executed");
		return FlatUtils.convertToFlatDto(flatEntity);
	}

	/*
	 * Description : This method Updates existing Flat
	 * Input Param : Flat Object 
	 * Return Value : FlatDTO Object 
	 * Exception : FlatBookingNotFoundException, InvalidFlatInputException
	 */
	@Override
	public FlatDTO updateFlat(Flat flat) throws FlatNotFoundException, InvalidFlatInputException {
		LOGGER.info("updateFlat() service is initiated");
		Flat flatEntity;
		if (flat == null)
			flatEntity = null;
		Flat existFlat = flatRepo.findById(flat.getFlatId()).orElse(null);
		if (existFlat == null)
			throw new FlatNotFoundException("Flat with given id was not found");
		else if (!validateFlat(flat))
			throw new InvalidFlatInputException("Invalid inputs for adding flat details");
		else
			flatEntity = flatRepo.save(flat);
		LOGGER.info("updateFlat() service has executed");
		return FlatUtils.convertToFlatDto(flatEntity);
	}

	/*
	 * Description : This method Deleted existing Flat
	 * Input Param : int 
	 * Return Value : FlatDTO Object 
	 * Exception : FlatBookingNotFoundException
	 */
	@Override
	public FlatDTO deleteFlat(int id) throws FlatNotFoundException {
		LOGGER.info("deleteFlat() service is initiated");
		Flat existFlat = flatRepo.findById(id).orElse(null);
		if (existFlat == null)
			throw new FlatNotFoundException("Flat with given id was not found");
		else
			flatRepo.delete(existFlat);
		LOGGER.info("deleteFlat() service has executed");
		return FlatUtils.convertToFlatDto(existFlat);
	}

	/*
	 * Description : This method Shaows existing Flat by ID
	 * Input Param : int
	 * Return Value : FlatDTO Object 
	 * Exception : FlatBookingNotFoundException
	 */
	@Override
	public FlatDTO viewFlat(int id) throws FlatNotFoundException {
		LOGGER.info("viewFlat() service is initiated");
		Flat existFlat = flatRepo.findById(id).orElse(null);
		if (existFlat == null)
			throw new FlatNotFoundException("Flat with given id was not found");
		LOGGER.info("viewFlat() service has executed");
		return FlatUtils.convertToFlatDto(existFlat);
	}

	/*
	 * Description : This method Shows all existing Flats
	 * Return Value : List<FlatDTO> Object 
	 */
	@Override
	public List<FlatDTO> viewAllFlat() {
		LOGGER.info("viewAllFlat() service is initiated");
		List<Flat> flatList = flatRepo.findAll();
		LOGGER.info("viewAllFlat() service has executed");
		return FlatUtils.convertToFlatDtoList(flatList);
	}

	@Override
	public List<FlatDTO> viewAllFlatByCost(float cost, String availability) throws InvalidFlatInputException,FlatNotFoundException {
		LOGGER.info("viewAllFlatByCost() service is initiated");
		List<Flat> flatList=null;
		if((validateFlatCost(cost)))
		{
		flatList = flatRepo.findByCostAndAvailability(cost, availability);
		LOGGER.info("viewAllFlatByCost() service has executed");
		}
		if(flatList==null)
		{
			throw new FlatNotFoundException(" No flat available for given cost");
		}
		return FlatUtils.convertToFlatDtoList(flatList);
	}

	public static boolean validateFlat(Flat flat) throws InvalidFlatInputException {
		LOGGER.info("validateFlat() is initiated");
		boolean flag = false;
		if (flat == null) {
			LOGGER.error("Flat details cannot be blank");
			throw new InvalidFlatInputException("Flat details cannot be blank");
		} else if (!(validateFlatHouseNo(flat.getFlatAddress().getHouseNo())
				&& validateFlatStreet(flat.getFlatAddress().getStreet())
				&& validateFlatCity(flat.getFlatAddress().getCity())
				&& validateFlatState(flat.getFlatAddress().getState())
				&& validateFlatCountry(flat.getFlatAddress().getCountry())
				&& validateFlatPin(flat.getFlatAddress().getPin()))) {
			LOGGER.error("Invalid Flat Address");
			throw new InvalidFlatInputException("Invalid Flat Address");
		} else if (!(validateFlatCost(flat.getFlatCost()))) {
			LOGGER.error("Invalid Cost");
			throw new InvalidFlatInputException("Invalid Cost");
		} else if (!(validateFlatAvailability(flat.getFlatAvailabilty()))) {
			LOGGER.error("Invalid Availability");
			throw new InvalidFlatInputException("Invalid Availability");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateCost() has executed");
		return flag;
	}

	public static boolean validateFlatCost(float cost) throws InvalidFlatInputException {
		LOGGER.info("validateFlatCost() is initiated");
		boolean flag = false;
		if (cost > 0) {
			flag = true;
			LOGGER.info("Validation Successful");
		} else {
			LOGGER.error("Cost cannot be 0 or a negative number");
			throw new InvalidFlatInputException("Cost cannot be 0 or a negative number");
		}
		LOGGER.info("validateFlatCost() has executed");
		return flag;
	}

	public static boolean validateFlatAvailability(String availability) throws InvalidFlatInputException {
		LOGGER.info("validateFlatAvailability() is initiated");
		boolean flag = false;
		if (availability.isBlank())
			throw new InvalidFlatInputException("Availability cannot be empty");
		if (availability.equals("YES") || availability.equals("Yes") || availability.equals("NO")
				|| availability.equals("No") || availability.equals("no") || availability.equals("n")
				|| availability.equals("N") || availability.equals("yes") || availability.equals("y")
				|| availability.equals("Y")) {
			{
				flag = true;
				LOGGER.info("Validation Successful");
			}
		} else {
			LOGGER.error("Availabilty can only be [YES | NO | Yes | No | Y | N | y | n]");
			throw new InvalidFlatInputException("Availabilty can only be [YES | NO | Yes | No | Y | N | y | n]");
		}
		LOGGER.info("validateFlatAvailability() has executed");
		return flag;
	}

	public static boolean validateFlatHouseNo(int houseNo) throws InvalidFlatInputException {
		LOGGER.info("validateFlatHouseNo() is initiated");
		boolean flag = false;
		if (!(Long.toString(houseNo).isBlank()) && houseNo > 0) {
			flag = true;
			LOGGER.info("Validation Successful");
		} else {
			LOGGER.error("HouseNo  cannot be empty or 0 or a negative number");
			throw new InvalidFlatInputException("HouseNo cannot be empty or 0 or a negative number");
		}
		LOGGER.info("validateFlatHouseNo() has executed");
		return flag;
	}

	public static boolean validateFlatStreet(String street) throws InvalidFlatInputException {
		LOGGER.info("validateFlatStreet() is initialised");
		boolean flag = false;
		if ((street.isBlank())) {
			LOGGER.error("Street name cannot be empty");
			throw new InvalidFlatInputException("Street name cannot be empty");
		} else if (!street.matches("^[a-zA-Z0-9 ]+$")) {
			LOGGER.error("Street cannot contain Special Characters");
			throw new InvalidFlatInputException("Street cannot contain Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateFlatStreet() has executed");
		return flag;
	}

	public static boolean validateFlatCity(String city) throws InvalidFlatInputException {
		LOGGER.info("validateFlatCity() is initialised");
		boolean flag = false;
		if ((city.isBlank())) {
			LOGGER.error("City name cannot be empty");
			throw new InvalidFlatInputException("City name cannot be empty");
		} else if (!city.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("City cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("City cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateFlatCity() has executed");
		return flag;
	}

	public static boolean validateFlatState(String state) throws InvalidFlatInputException {
		LOGGER.info("validateFlatState() is initialised");
		boolean flag = false;
		if ((state.isBlank())) {
			LOGGER.error("State name cannot be empty");
			throw new InvalidFlatInputException("State name cannot be empty");
		} else if (!state.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("State cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("State cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateFlatState() has executed");
		return flag;
	}

	public static boolean validateFlatCountry(String country) throws InvalidFlatInputException {
		LOGGER.info("validateFlatCountry() is initiated");
		boolean flag = false;
		if (((country.isBlank()))) {
			LOGGER.error("Country name cannot be empty");
			throw new InvalidFlatInputException("Country name cannot be empty");
		} else if (!country.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("Country cannot contain Numbers or Special Characters");
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateFlatCountry() has executed");
		return flag;
	}

	public static boolean validateFlatPin(long pin) throws InvalidFlatInputException {
		LOGGER.info("validateFlatPin() is initiated");
		boolean flag = false;
		if (pin <= 0) {
			LOGGER.error("PinCode cannot be 0 or negative");
			throw new InvalidFlatInputException("PinCode cannot be 0 or negative");
		} else if (Long.toString(pin).length() != 6) {
			LOGGER.error("PinCode should be length 6");
			throw new InvalidFlatInputException("PinCode should be length 6");
		} else if (!Long.toString(pin).matches("^[0-9]+$")) {
			LOGGER.error("PinCode cannot contain any Characters");
			throw new InvalidFlatInputException("PinCode cannot contain any Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateFlatPin() has executed");
		return flag;
	}

}
