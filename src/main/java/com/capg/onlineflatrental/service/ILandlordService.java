package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;

public interface ILandlordService {

	public LandlordDTO addLandlord(Landlord landlord);
	public LandlordDTO updateLandlord(Landlord landlord) throws LandlordNotFoundException;
	public LandlordDTO deleteLandlord(Landlord landlord) throws LandlordNotFoundException;
	public LandlordDTO viewLandlord(int id) throws LandlordNotFoundException;
	public List<LandlordDTO> viewAllLandlord();
	
}
