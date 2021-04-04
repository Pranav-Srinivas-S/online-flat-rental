package com.capg.onlineflatrental.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
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

}
