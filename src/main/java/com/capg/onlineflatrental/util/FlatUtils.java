package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.model.FlatDTO;

public class FlatUtils {

	private FlatUtils() {
		
	}
	
	public static List<FlatDTO> convertToFlatDtoList(List<Flat> list){
		List<FlatDTO> dtolist = new ArrayList<>();
		for(Flat flat : list) 
			dtolist.add(convertToFlatDto(flat));
		return dtolist;
	}
	
	public static Flat convertToFlat(FlatDTO dto) {
		Flat flat = new Flat();
		flat.setFlatId(dto.getFlatId());
		flat.setFlatCost(dto.getFlatCost());
		flat.setFlatAddress(dto.getFlatAddress());
		flat.setFlatAvailabilty(dto.getFlatAvailabilty());
		return flat;
	}		
	
	public static FlatDTO convertToFlatDto(Flat flat) {
		FlatDTO dto = new FlatDTO();
		dto.setFlatId(flat.getFlatId());
		dto.setFlatCost(flat.getFlatCost());
		dto.setFlatAddress(flat.getFlatAddress());
		dto.setFlatAvailabilty(flat.getFlatAvailabilty());
		return dto;
	}
	
}
