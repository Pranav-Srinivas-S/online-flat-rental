package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.exception.FlatNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.model.FlatDTO;

public interface IFlatService {

	public FlatDTO addFlat(Flat flat) throws InvalidFlatInputException;
	public FlatDTO updateFlat(Flat flat) throws FlatNotFoundException, InvalidFlatInputException;
	public FlatDTO deleteFlat(int id) throws FlatNotFoundException;
	public FlatDTO viewFlat(int id) throws FlatNotFoundException;
	public List<FlatDTO> viewAllFlat();
	public List<FlatDTO> viewAllFlatByCost(float cost,String availability);
	
}
