package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.model.FlatBookingDTO;

public class FlatBookingUtils {

	public static List<FlatBookingDTO> convertToFlatBookingDtoList(List<FlatBooking> list){
		List<FlatBookingDTO> dtolist = new ArrayList<FlatBookingDTO>();
		for(FlatBooking flatBooking : list) 
			dtolist.add(convertToFlatBookingDto(flatBooking));
		return dtolist;
	}
	
	public static FlatBooking convertToFlatBooking(FlatBookingDTO dto) {
		FlatBooking flatBooking = new FlatBooking();
		flatBooking.setBookingNo(dto.getBookingNo());
		flatBooking.setFlat(dto.getFlat());
		flatBooking.setTenantId(dto.getTenantId());
		flatBooking.setBookingFromDate(dto.getBookingFromDate());
		flatBooking.setBookingToDate(dto.getBookingToDate());
		return flatBooking;
	}		
	
	public static FlatBookingDTO convertToFlatBookingDto(FlatBooking flatBooking) {
		FlatBookingDTO dto = new FlatBookingDTO();
		dto.setBookingNo(flatBooking.getBookingNo());
		dto.setFlat(flatBooking.getFlat());
		dto.setTenantId(flatBooking.getTenantId());
		dto.setBookingFromDate(flatBooking.getBookingFromDate());
		dto.setBookingToDate(flatBooking.getBookingToDate());
		return dto;
	}
	
}
