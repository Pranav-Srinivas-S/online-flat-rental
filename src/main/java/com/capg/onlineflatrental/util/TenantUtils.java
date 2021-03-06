package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.model.TenantDTO;

public class TenantUtils {

	private TenantUtils() {
		
	}
	
	public static List<TenantDTO> convertToTenantDtoList(List<Tenant> list){
		List<TenantDTO> dtolist = new ArrayList<>();
		for(Tenant tenant : list) 
			dtolist.add(convertToTenantDto(tenant));
		return dtolist;
	}
	
	public static Tenant convertToTenant(TenantDTO dto) {
		Tenant tenant = new Tenant();
		tenant.setTenantId(dto.getTenantId());
		tenant.setTenantName(dto.getTenantName());
		tenant.setTenantAge(dto.getTenantAge());
		tenant.setTenantAddress(dto.getTenantAddress());
		return tenant;
	}		
	
	public static TenantDTO convertToTenantDto(Tenant tenant) {
		TenantDTO dto = new TenantDTO();
		dto.setTenantId(tenant.getTenantId());
		dto.setTenantName(tenant.getTenantName());
		dto.setTenantAge(tenant.getTenantAge());
		dto.setTenantAddress(tenant.getTenantAddress());
		return dto;
	}
	
}
