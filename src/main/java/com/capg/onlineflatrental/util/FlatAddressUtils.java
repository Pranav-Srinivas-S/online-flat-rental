package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.model.FlatAddressDTO;

public class FlatAddressUtils {

	private FlatAddressUtils() {
		
	}
	
	public static List<FlatAddressDTO> convertToFlatAddressDtoList(List<FlatAddress> list){
		List<FlatAddressDTO> dtolist = new ArrayList<>();
		for(FlatAddress flatAddress : list) 
			dtolist.add(convertToFlatAddressDto(flatAddress));
		return dtolist;
	}
	
	public static FlatAddress convertToFlatAddress(FlatAddressDTO dto) {
		FlatAddress flatAddress = new FlatAddress();
		flatAddress.setHouseNo(dto.getHouseNo());
		flatAddress.setStreet(dto.getStreet());
		flatAddress.setCity(dto.getCity());
		flatAddress.setState(dto.getState());
		flatAddress.setPin(dto.getPin());
		flatAddress.setCountry(dto.getCountry());
		return flatAddress;
	}		
	
	public static FlatAddressDTO convertToFlatAddressDto(FlatAddress flatAddress) {
		FlatAddressDTO dto = new FlatAddressDTO();
		dto.setHouseNo(flatAddress.getHouseNo());
		dto.setStreet(flatAddress.getStreet());
		dto.setCity(flatAddress.getCity());
		dto.setState(flatAddress.getState());
		dto.setPin(flatAddress.getPin());
		dto.setCountry(flatAddress.getCountry());
		return dto;
	}
	
}
