package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.exception.FlatNotFoundException;

public interface IFlatService {

	public Flat addFlat(Flat flat);
	public Flat updateFlat(Flat flat) throws FlatNotFoundException;
	public Flat deleteFlat(int id) throws FlatNotFoundException;
	public Flat viewFlat(int id) throws FlatNotFoundException;
	public List<Flat> viewAllFlat();
	public List<Flat> viewAllFlatByCost(float cost,String availability);
	
}
