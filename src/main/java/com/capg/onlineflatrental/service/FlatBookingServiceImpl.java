package com.capg.onlineflatrental.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;
import com.capg.onlineflatrental.repository.IFlatBookingRepository;
import com.capg.onlineflatrental.util.FlatBookingUtils;

/*
 * Author : SHAIK ABDUL BASHEER 
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Flat Booking Controller
*/

@Service
public class FlatBookingServiceImpl implements IFlatBookingService {

	static final Logger LOGGER = LoggerFactory.getLogger(FlatBookingServiceImpl.class);
	
	String noFlat = "No FlatBooking found in given ID";
	
	static String validationSuccessful = "Validation Successful";
	
	@Autowired
	private IFlatBookingRepository flatbookingRepo;

	/*
	 * Description : This method Adds new Flat Booking 
	 * Input Param : FlatBooking Object 
	 * Return Value : FlatBookingDTO Object 
	 * Exception : FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException
	 */
	@Override
	public FlatBookingDTO addFlatBooking(FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("addFlatBooking() service is initiated");
		FlatBooking flatbookingEntity;
		if (flatBooking == null)
			flatbookingEntity = null;
		else
		{
			validateFlatBooking(flatBooking);
			flatbookingEntity = flatbookingRepo.save(flatBooking);
		}
		LOGGER.info("addFlatBooking() service has executed");
		return FlatBookingUtils.convertToFlatBookingDto(flatbookingEntity);
	}

	/*
	 * Description : This method Updates existing Flat Booking 
	 * Input Param : FlatBooking Object 
	 * Return Value : FlatBookingDTO Object 
	 * Exception : FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException
	 */
	@Override
	public FlatBookingDTO updateFlatBooking(FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("updateFlatBooking() service is initiated");
		FlatBooking flatbookingEntity;
		FlatBooking existFlatBooking = flatbookingRepo.findById(flatBooking.getBookingNo()).orElse(null);
		if (existFlatBooking == null)
			throw new FlatBookingNotFoundException(noFlat);
		else
		{
			validateFlatBooking(flatBooking);
			flatbookingEntity = flatbookingRepo.save(flatBooking);
		}
		LOGGER.info("updateFlatBooking() service has executed");
		return FlatBookingUtils.convertToFlatBookingDto(flatbookingEntity);
	}

	/*
	 * Description : This method Deletes existing Flat Booking 
	 * Input Param : int
	 * Return Value : FlatBookingDTO Object 
	 * Exception : FlatBookingNotFoundException
	 */
	@Override
	public FlatBookingDTO deleteFlatBooking(int id) throws FlatBookingNotFoundException {
		LOGGER.info("deleteFlatBooking() service is initiated");
		FlatBooking existFlatBooking = flatbookingRepo.findById(id).orElse(null);
		if (existFlatBooking == null)
			throw new FlatBookingNotFoundException(noFlat);
		else
			flatbookingRepo.delete(existFlatBooking);
		LOGGER.info("deleteFlatBooking() service has executed");
		return FlatBookingUtils.convertToFlatBookingDto(existFlatBooking);
	}

	/*
	 * Description : This method Views existing Flat Booking by ID 
	 * Input Param : int
	 * Return Value : FlatBookingDTO Object 
	 * Exception : FlatBookingNotFoundException
	 */
	@Override
	public FlatBookingDTO viewFlatBooking(int id) throws FlatBookingNotFoundException {
		LOGGER.info("viewFlatBooking() service is initiated");
		FlatBooking existFlatBooking = flatbookingRepo.findById(id).orElse(null);
		if (existFlatBooking == null)
			throw new FlatBookingNotFoundException(noFlat);
		LOGGER.info("viewFlatBooking() service has executed");
		return FlatBookingUtils.convertToFlatBookingDto(existFlatBooking);
	}

	/*
	 * Description : This method Views all existing Flat Bookings 
	 * Return Value : List<FlatBookingDTO> Object
	 */
	@Override
	public List<FlatBookingDTO> viewAllFlatBooking() {
		LOGGER.info("viewAllFlatBooking() service is initiated");
		List<FlatBooking> flatbookingList = flatbookingRepo.findAll();
		LOGGER.info("viewAllFlatBooking() service has executed");
		return FlatBookingUtils.convertToFlatBookingDtoList(flatbookingList);
	}

	public static boolean validateFlatBooking(FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("validateFlatBooking() is initiated");
		boolean flag = false;
		if (flatBooking == null) {
			LOGGER.error("Flat Booking details cannot be blank");
			throw new FlatBookingNotFoundException("Flat Booking details cannot be blank");
		} else {
			validateBookingFromDate(flatBooking);
			validateBookingToDate(flatBooking);
			TenantServiceImpl.validateTenant(flatBooking.getTenant());
			FlatServiceImpl.validateFlat(flatBooking.getFlat());
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateFlatBooking() has executed");
		return flag;

	}

	public static boolean validateBookingFromDate(FlatBooking flatbooking) throws FlatBookingNotFoundException {
		LOGGER.info("validateFlatBookingFromDate() is initiated");
		boolean flag = false;
		if (flatbooking.getBookingFromDate() == null) {
			LOGGER.error("Booking_From_Date cannot be empty");
			throw new FlatBookingNotFoundException("Booking_From_Date cannot be empty");
		} else if (flatbooking.getBookingFromDate().isAfter(LocalDate.now())) {
			LOGGER.error("Booking_From_Date cannot be after Current_System_Date");
			throw new FlatBookingNotFoundException("Booking_From_Date cannot be after Current_System_Date");
		} else {
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateFlatBookingFromDate() has executed");
		return flag;
	}

	public static boolean validateBookingToDate(FlatBooking flatbooking) throws FlatBookingNotFoundException {
		LOGGER.info("validateFlatBookingtoDate() is initiated");
		boolean flag = false;
		if (flatbooking.getBookingToDate() == null) {
			LOGGER.error("Booking_To_Date cannot be empty");
			throw new FlatBookingNotFoundException("Booking_To_Date cannot be empty");
		} else if (flatbooking.getBookingToDate().isBefore(flatbooking.getBookingFromDate())) {
			LOGGER.error("Booking_To_Date cannot be before Booking_From_Date");
			throw new FlatBookingNotFoundException("Booking_To_Date cannot be before Booking_From_Date");
		} else {
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateFlatBookingToDate() has executed");
		return flag;
	}

	public boolean validateFlatBookingId(int id) throws FlatBookingNotFoundException {
		LOGGER.info("validateFlatBookingId() is initiated");
		boolean flag = false;
		String flatbookingNotFound = "flatbooking id not found";
		if (!flatbookingRepo.existsById(id)) {
			LOGGER.error("flatbooking id not found");
			throw new FlatBookingNotFoundException(flatbookingNotFound);
		} 
		else
			flag = true;
		LOGGER.info(validationSuccessful);
		LOGGER.info("validateFlatBookingId() has executed");
		return flag;
	}

}
