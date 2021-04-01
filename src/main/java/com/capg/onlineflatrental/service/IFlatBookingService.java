package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;
import com.capg.onlineflatrental.model.FlatDTO;

public interface IFlatBookingService {

	public FlatDTO addFlatBooking(FlatBooking flat) ;
	public FlatDTO updateFlatBooking(FlatBooking flat) throws FlatBookingNotFoundException;
	public FlatDTO deleteFlatBooking(int id) throws FlatBookingNotFoundException;
	public FlatDTO viewFlatBooking(int id) throws FlatBookingNotFoundException;
	public List<FlatBookingDTO> viewAllFlatBooking();
	
}
