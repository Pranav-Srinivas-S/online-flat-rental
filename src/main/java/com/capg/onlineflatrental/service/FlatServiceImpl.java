package com.capg.onlineflatrental.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.exception.FlatNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.model.FlatDTO;
import com.capg.onlineflatrental.repository.IFlatRepository;
import com.capg.onlineflatrental.util.FlatUtils;

@Service
public class FlatServiceImpl implements IFlatService {
	
	@Autowired
	private IFlatRepository flatRepo;

	@Override
	public FlatDTO addFlat(Flat flat) {
		Flat flatEntity;
		if(flat == null)
			flatEntity = null;
		else
			flatEntity = flatRepo.save(flat);
		return FlatUtils.convertToFlatDto(flatEntity);
	}
	
	@Override
	public FlatDTO updateFlat(Flat flat) throws FlatNotFoundException {
		Flat flatEntity;
		if(flat == null)
			flatEntity = null;
		Flat existFlat = flatRepo.findById(flat.getFlatId()).orElse(null);
		if(existFlat == null)
			throw new FlatNotFoundException("Flat with given id was not found");
		else
			flatEntity = flatRepo.save(flat);
		return FlatUtils.convertToFlatDto(flatEntity);
	}
	
	@Override
	public FlatDTO deleteFlat(int id) throws FlatNotFoundException {
	
		Flat existFlat =flatRepo.findById(id).orElse(null);
		if(existFlat == null)
			throw new FlatNotFoundException("Flat with given id was not found");
		else
			flatRepo.delete(existFlat);
		return FlatUtils.convertToFlatDto(existFlat);
	}

	@Override
	public FlatDTO viewFlat(int id) throws FlatNotFoundException {
		Flat existFlat = flatRepo.findById(id).orElse(null);
		if(existFlat == null)
			throw new FlatNotFoundException("Flat with given id was not found");
		return FlatUtils.convertToFlatDto(existFlat);
	}
	
	

	@Override
	public List<FlatDTO> viewAllFlat() {
		List<Flat> flatList = flatRepo.findAll();
		return FlatUtils.convertToFlatDtoList(flatList);
	}

	@Override
	public List<FlatDTO> viewAllFlatByCost(float cost, String availability) {
		List<Flat>   flatList=flatRepo.findByCostAndAvailability(cost,availability);
		return FlatUtils.convertToFlatDtoList(flatList);
	}
	
	public static boolean validateFlat(Flat flat) throws InvalidFlatInputException
	{
		boolean flag = false;
		if(flat == null)
			throw new InvalidFlatInputException("Flat details cannot be blank");
		else if(!(validateFlatHouseNo(flat.getFlatAddress().getHouseNo())
				&& validateFlatStreet(flat.getFlatAddress().getStreet()) && validateFlatCity(flat.getFlatAddress().getCity())
				&& validateFlatState(flat.getFlatAddress().getState()) && validateFlatCountry(flat.getFlatAddress().getCountry())
				&& validateFlatPin(flat.getFlatAddress().getPin())))
			throw new InvalidFlatInputException("Invalid Flat Address");
		else if(!(validateFlatCost(flat.getFlatCost())))
				throw new InvalidFlatInputException("Invalid Cost");
		else if(!(validateFlatAvailability(flat.getFlatAvailabilty())))
			throw new InvalidFlatInputException("Invalid Availability");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateFlatCost(float cost) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(cost > 0)
			flag=true;
		else 
			throw new InvalidFlatInputException("Cost cannot be 0 or a negative number");
		return flag;
	}
	
	public static boolean validateFlatAvailability(String availability) throws InvalidFlatInputException
	{
		boolean flag=false;
		
		if((availability.isBlank()))
			throw new InvalidFlatInputException("Availability cannot be empty");
		if(availability.equals("YES")||availability.equals("Yes") ||
				availability.equals("NO")||availability.equals("No") ||
				availability.equals("no")||availability.equals("n") || availability.equals("N") ||
				availability.equals("yes")||availability.equals("y") || availability.equals("Y"))
				{
					 flag =true;
				}
				else
					throw new InvalidFlatInputException("Availabilty can only be [YES | NO | Yes | No | Y | N | y | n]");
		return flag;
	}
	
	public static boolean validateFlatHouseNo(int houseNo) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(!(Long.toString(houseNo).isBlank()) && houseNo > 0)
			flag=true;
		else
			throw new InvalidFlatInputException("HouseNo name cannot be empty or 0 or a negative number");
		return flag;
	}
	
	public static boolean validateFlatStreet(String street) throws InvalidFlatInputException
	{
		boolean flag=false;
		if((street.isBlank()))
			throw new InvalidFlatInputException("Street name cannot be empty");
		else if(!street.matches("^[a-zA-Z0-9 ]+$"))
			throw new InvalidFlatInputException("Street cannot contain Special Characters");
		else
			flag=true;
		return flag;
	}
	
	public static boolean validateFlatCity(String city) throws InvalidFlatInputException
	{
		boolean flag=false;
		if((city.isBlank()))
			throw new InvalidFlatInputException("City name cannot be empty");
		else if(!city.matches("^[a-zA-Z ]+$"))
			throw new InvalidFlatInputException("City cannot contain Numbers or Special Characters");
		else
			flag=true;
		return flag;
	}
	
	public static boolean validateFlatState(String state) throws InvalidFlatInputException
	{
		boolean flag=false;
		if((state.isBlank()))
			throw new InvalidFlatInputException("State name cannot be empty");
		else if(!state.matches("^[a-zA-Z ]+$"))
			throw new InvalidFlatInputException("State cannot contain Numbers or Special Characters");
		else
			flag=true;
		return flag;
	}
	
	public static boolean validateFlatCountry(String country) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(((country.isBlank())))
			throw new InvalidFlatInputException("Country name cannot be empty");
		else if(!country.matches("^[a-zA-Z ]+$"))
			throw new InvalidFlatInputException("Country cannot contain Numbers or Special Characters");
		else
			flag=true;
		return flag;
	}
	
	public static boolean validateFlatPin(long pin) throws InvalidFlatInputException
	{
		boolean flag = false;
	if(pin <= 0)
		throw new InvalidFlatInputException("PinCode cannot be 0 or negative");
	else if(Long.toString(pin).length() != 6)
		throw new InvalidFlatInputException("PinCode should be length 6");
	else if(!Long.toString(pin).matches("^[0-9]+$"))
			throw new InvalidFlatInputException("PinCode cannot contain any Characters");
	else
		flag = true;
	return flag;
	}

}
	
	
	



