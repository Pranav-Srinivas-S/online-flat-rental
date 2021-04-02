package com.capg.onlineflatrental.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.TenantDTO;

@Service
public class TenantServiceImpl implements ITenantService {

	@Override
	public TenantDTO addTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenantDTO updateTenant(Tenant tenant) throws TenantNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenantDTO deleteTenant(int id) throws TenantNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenantDTO viewTenant(int id) throws TenantNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TenantDTO> viewAllTenant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenantDTO validateTenant(int tenantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
