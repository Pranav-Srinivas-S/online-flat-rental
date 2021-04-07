package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;

public interface IFlatBookingService {

	public FlatBookingDTO addFlatBooking(FlatBooking flat) throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException ;
	public FlatBookingDTO updateFlatBooking(FlatBooking flat) throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException;
	public FlatBookingDTO deleteFlatBooking(int id) throws FlatBookingNotFoundException;
	public FlatBookingDTO viewFlatBooking(int id) throws FlatBookingNotFoundException;
	public List<FlatBookingDTO> viewAllFlatBooking();
	
}