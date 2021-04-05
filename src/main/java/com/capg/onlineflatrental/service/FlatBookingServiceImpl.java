package com.capg.onlineflatrental.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;
import com.capg.onlineflatrental.repository.IFlatBookingRepository;
import com.capg.onlineflatrental.util.FlatBookingUtils;

@Service
public class FlatBookingServiceImpl implements IFlatBookingService {
	@Autowired
	static IFlatBookingRepository flatbookingRepo;

	@Override
	public FlatBookingDTO addFlatBooking(FlatBooking  flatbooking) {
		FlatBooking flatbookingEntity;
		if(flatbooking == null)
			flatbookingEntity = null;
		else
			flatbookingEntity = flatbookingRepo.save(flatbooking);
		return FlatBookingUtils.convertToFlatBookingDto(flatbookingEntity);
	}
	
	@Override
	public FlatBookingDTO updateFlatBooking(FlatBooking flatbooking) throws FlatBookingNotFoundException {
		FlatBooking flatbookingEntity;
		if(flatbooking== null)
			flatbookingEntity = null;
		FlatBooking existFlatBooking =  flatbookingRepo.findById(flatbooking.getBookingNo()).orElse(null);
		if(existFlatBooking == null)
			throw new FlatBookingNotFoundException("No FlatBooking found in given ID");
		else
			flatbookingEntity = flatbookingRepo.save(flatbooking);
		return FlatBookingUtils.convertToFlatBookingDto(flatbookingEntity);
	}

	@Override
	public FlatBookingDTO deleteFlatBooking(int id) throws FlatBookingNotFoundException {
		FlatBooking existFlatBooking= flatbookingRepo.findById(id).orElse(null);
		if(existFlatBooking == null)
			throw new FlatBookingNotFoundException("No FlatBooking found in given ID");
		else
			flatbookingRepo.delete(existFlatBooking);
		return FlatBookingUtils.convertToFlatBookingDto(existFlatBooking);
	}

	@Override
	public  FlatBookingDTO viewFlatBooking(int id) throws FlatBookingNotFoundException {
		FlatBooking existFlatBooking = flatbookingRepo.findById(id).orElse(null);
		if(existFlatBooking == null)
			throw new FlatBookingNotFoundException("No FlatBooking found in given ID");
		return  FlatBookingUtils.convertToFlatBookingDto(existFlatBooking);
	}

	@Override
	public List<FlatBookingDTO> viewAllFlatBooking() {
		List<FlatBooking> flatbookingList =  flatbookingRepo.findAll();
		return FlatBookingUtils.convertToFlatBookingDtoList(flatbookingList);
	}

	public static boolean validateFlatBooking(FlatBooking flatBooking) throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		boolean flag = false;
		if (flatBooking==null)
			throw new FlatBookingNotFoundException("Flat Booking details cannot be blank");
		else if (!(validateBookingFromDate(flatBooking)))
			throw new FlatBookingNotFoundException("Flat Booking From Date cannot be empty or after current date");
		else if (!(validateBookingToDate(flatBooking)))
			throw new FlatBookingNotFoundException("Flat Booking To Date cannot be empty or before Flat Booking From Date");
		else if (!TenantServiceImpl.validateTenant(flatBooking.getTenant()))
			throw new FlatBookingNotFoundException("Invalid Tenant Details");
		else if (!FlatServiceImpl.validateFlat(flatBooking.getFlat()))
			throw new FlatBookingNotFoundException("Invalid Flat Details");
		else
			flag = true;
		return flag;
			
	}
	public static boolean validateBookingFromDate(FlatBooking flatbooking) {
		boolean flag = true;
		if (flatbooking.getBookingFromDate() == null || flatbooking.getBookingFromDate().isAfter(LocalDate.now()))
			flag = false;
		return flag;
	}
	public static boolean validateBookingToDate(FlatBooking flatbooking) {
		boolean flag = true;
		if (flatbooking.getBookingToDate() == null || flatbooking.getBookingToDate().isAfter(flatbooking.getBookingFromDate()))
			flag = false;
		return flag;
	}

	public boolean validateFlatBookingId(int id) throws FlatBookingNotFoundException
	{
		boolean flag = flatbookingRepo.existsById(id);
		String flatbookingNotFound ="flatbooking id not found";
		if(flag == false)
			throw new FlatBookingNotFoundException(flatbookingNotFound);
		return flag;
	}

}
