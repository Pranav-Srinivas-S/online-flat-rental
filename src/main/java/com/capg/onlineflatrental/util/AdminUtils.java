package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.model.AdminDTO;

public class AdminUtils {

	public static List<AdminDTO> convertToAdminDtoList(List<Admin> list){
		List<AdminDTO> dtolist = new ArrayList<AdminDTO>();
		for(Admin admin : list) 
			dtolist.add(convertToAdminDto(admin));
		return dtolist;
	}
	
	public static Admin convertToAdmin(AdminDTO dto) {
		Admin admin = new Admin();
		admin.setAdminId(dto.getAdminId());
		admin.setAdminPassword(dto.getAdminPassword());
		return admin;
	}		
	
	public static AdminDTO convertToAdminDto(Admin admin) {
		AdminDTO dto = new AdminDTO();
		dto.setAdminId(admin.getAdminId());;
		dto.setAdminPassword(admin.getAdminPassword());
		return dto;
	}
	
}
