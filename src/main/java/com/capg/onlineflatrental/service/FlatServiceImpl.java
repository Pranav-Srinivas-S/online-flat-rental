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
	IFlatRepository repo;

	@Override
	public FlatDTO addFlat(Flat flat) {
		Flat flatEntity=null;
	try {
		if(FlatServiceImpl.validateCost(flat.getFlatCost()) && FlatServiceImpl.validateAvailability(flat.getFlatAvailabilty())
		            && FlatServiceImpl.validatePin(flat.getFlatAddress().getPin()) && FlatServiceImpl.validateStreet(flat.getFlatAddress().getStreet())
					&& FlatServiceImpl.validateHouseNo(flat.getFlatAddress().getHouseNo()) && FlatServiceImpl.validateCity(flat.getFlatAddress().getCity())
					&& FlatServiceImpl.validateState(flat.getFlatAddress().getState()) && FlatServiceImpl.validateCountry(flat.getFlatAddress().getCountry()))
			{
			flatEntity=repo.save(flat);
			}
	} catch (InvalidFlatInputException e) {
		e.printStackTrace();
	}
		return FlatUtils.convertToFlatDto(flatEntity);
		
	}
	
	

	@Override
	public FlatDTO updateFlat(Flat flat) throws FlatNotFoundException {
		Flat flatEntity=null;
		try {
			if(FlatServiceImpl.validateCost(flat.getFlatCost()) && FlatServiceImpl.validateAvailability(flat.getFlatAvailabilty())
		            && FlatServiceImpl.validatePin(flat.getFlatAddress().getPin()) && FlatServiceImpl.validateStreet(flat.getFlatAddress().getStreet())
					&& FlatServiceImpl.validateHouseNo(flat.getFlatAddress().getHouseNo()) && FlatServiceImpl.validateCity(flat.getFlatAddress().getCity())
					&& FlatServiceImpl.validateState(flat.getFlatAddress().getState()) && FlatServiceImpl.validateCountry(flat.getFlatAddress().getCountry()))
				{
		flatEntity=repo.save(flat);
				}
		} catch (InvalidFlatInputException e) {
			e.printStackTrace();
		}
		return FlatUtils.convertToFlatDto(flatEntity);
	}
	
	

	@Override
	public void deleteFlat(int id) throws FlatNotFoundException {
	
			if (repo.existsById(id)) 
		        repo.deleteById(id);
			else 
				throw new FlatNotFoundException("Flat with id " + id + " was not found");
	}
	
	

	@Override
	public FlatDTO viewFlat(int id) throws FlatNotFoundException {
		Flat flatEntity=null;
			if (repo.existsById(id)) 
			{
				flatEntity=repo.findById(id).orElse(null);
	             return FlatUtils.convertToFlatDto(flatEntity);
			}
			else
				throw new FlatNotFoundException("Flat with id " + id + " was not found");	
	}
	
	

	@Override
	public List<FlatDTO> viewAllFlat() {
		List<Flat> flatEntity=repo.findAll();
		return FlatUtils.convertToFlatDtoList(flatEntity);
	}
	
	

	@Override
	public List<FlatDTO> viewAllFlatByCost(float cost, String availability) {
		List<Flat>   flatEntity=null;
		try
		{
			if(FlatServiceImpl.validateCost(cost) && FlatServiceImpl.validateAvailability(availability))
			{
		flatEntity=repo.findByCostAndAvailability(cost,availability);
			}
		}
			catch(InvalidFlatInputException e) {
				e.printStackTrace();
			}
		return FlatUtils.convertToFlatDtoList(flatEntity);
	}
	
	
	
	public static boolean validateCost(float cost) throws InvalidFlatInputException
	{
	boolean flag=true;
	
	if(cost>=0)
		flag=true;
	else 
		throw new InvalidFlatInputException("Cost cannot be 0 or a negative number");
	return flag;
	}
	
	
	
	public static boolean validateAvailability(String availability) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(availability.equals("YES")||availability.equals("Yes")||
				availability.equals("NO")||availability.equals("No"))
				{
					 flag =true;
				}
				else
					throw new InvalidFlatInputException("Availabilty can only be [YES|NO|Yes|No]");
		return flag;
	}
	
	
	
	public static boolean validatePin(int pin) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(pin> 0 && Long.toString(pin).length() == 6 && Long.toString(pin).matches("^[0-9]+$"))
			flag = true;
		else
			throw new InvalidFlatInputException("Pincode cannot be 0 or negative, "
					+ "length should be 6 and should be a number");
		return flag;
	}
	
	
	
	
	public static boolean validateStreet(String street) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(!(street.isBlank()))
			flag=true;
		else
			throw new InvalidFlatInputException("Street name cannot be empty");
		return flag;
	}
	
	
	
	public static boolean validateHouseNo(int houseNo) throws InvalidFlatInputException
	{
		boolean flag=false;
		if(!(Long.toString(houseNo).isBlank()))
			flag=true;
		else
			throw new InvalidFlatInputException("HouseNo name cannot be empty");
		return flag;
	}
	
	
	
	public static boolean validateCity(String city) throws InvalidFlatInputException
	{
		boolean flag=false;
		if((!(city.isBlank())) && city.matches("^[a-zA-Z]*$"))
		flag=true;
		else
			throw new InvalidFlatInputException("City name cannot be empty and cannot have "
					+ "numbers or special characters");
		return flag;
	}
	
	
	
	public static boolean validateState(String state) throws InvalidFlatInputException
	{
		boolean flag=false;
		if((!(state.isBlank())) &&state.matches("^[a-zA-Z]*$"))
		flag=true;
		else
			throw new InvalidFlatInputException("State name cannot be empty and cannot have "
					+ "numbers or special characters");
		return flag;
	}
	
	
	
	public static boolean validateCountry(String country) throws InvalidFlatInputException
	{
		boolean flag=false;
		if((!(country.isBlank())) &&country.matches("^[a-zA-Z]*$"))
		flag=true;
		else
			throw new InvalidFlatInputException("Country name cannot be empty and cannot have "
					+ "numbers or special characters");
		return flag;
	}
	}
	
	
	



