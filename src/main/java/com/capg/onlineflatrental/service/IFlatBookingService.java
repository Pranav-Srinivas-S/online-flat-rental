package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;

public interface IFlatBookingService {

	public Flat addFlatBooking(FlatBooking flat) ;
	public Flat updateFlatBooking(FlatBooking flat) throws FlatBookingNotFoundException;
	public Flat deleteFlatBooking(int id) throws FlatBookingNotFoundException;
	public Flat viewFlatBooking(int id) throws FlatBookingNotFoundException;
	public List<FlatBooking> viewAllFlatBooking();
	
}
