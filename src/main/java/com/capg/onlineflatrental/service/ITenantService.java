package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.TenantDTO;

public interface ITenantService {

	public TenantDTO addTenant(Tenant tenant) ;
	public TenantDTO updateTenant(Tenant tenant) throws TenantNotFoundException;
	public void deleteTenant(int id) throws TenantNotFoundException;
	public TenantDTO viewTenant(int id) throws TenantNotFoundException;
	public List<TenantDTO> viewAllTenant();
	
}
