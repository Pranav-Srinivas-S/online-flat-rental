package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;

public interface ILandlordService {

	public LandlordDTO addLandlord(Landlord landlord) throws LandlordNotFoundException, InvalidFlatInputException;
	public LandlordDTO updateLandlord(Landlord landlord) throws LandlordNotFoundException, InvalidFlatInputException;
	public LandlordDTO deleteLandlord(int id) throws LandlordNotFoundException;
	public LandlordDTO viewLandlord(int id) throws LandlordNotFoundException;
	public List<LandlordDTO> viewAllLandlord();
	
}
