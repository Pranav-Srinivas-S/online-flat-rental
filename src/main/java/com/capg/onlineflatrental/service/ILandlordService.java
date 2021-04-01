package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;

public interface ILandlordService {

	public Landlord addLandlord(Landlord landlord);
	public Landlord updateLandlord(Landlord landlord) throws LandlordNotFoundException;
	public Landlord deleteLandlord(Landlord landlord) throws LandlordNotFoundException;
	public Landlord viewLandlord(int id) throws LandlordNotFoundException;
	public List<Landlord> viewAllLandlord();
	
}
