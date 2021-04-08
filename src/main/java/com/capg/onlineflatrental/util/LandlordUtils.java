package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.model.LandlordDTO;

public class LandlordUtils {

	private LandlordUtils() {
		
	}
	
	public static List<LandlordDTO> convertToLandlordDtoList(List<Landlord> list){
		List<LandlordDTO> dtolist = new ArrayList<>();
		for(Landlord landlord : list) 
			dtolist.add(convertToLandlordDto(landlord));
		return dtolist;
	}
	
	public static Landlord convertToLandlord(LandlordDTO dto) {
		Landlord landlord = new Landlord();
		landlord.setLandlordId(dto.getLandlordId());
		landlord.setLandlordName(dto.getLandlordName());
		landlord.setLandlordAge(dto.getLandlordAge());
		landlord.setFlatList(dto.getFlatList());
		return landlord;
	}		
	
	public static LandlordDTO convertToLandlordDto(Landlord landlord) {
		LandlordDTO dto = new LandlordDTO();
		dto.setLandlordId(landlord.getLandlordId());
		dto.setLandlordName(landlord.getLandlordName());
		dto.setLandlordAge(landlord.getLandlordAge());
		dto.setFlatList(landlord.getFlatList());
		return dto;
	}
	
}
