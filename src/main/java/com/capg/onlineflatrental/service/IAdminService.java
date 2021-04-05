package com.capg.onlineflatrental.service;

import java.util.List;

import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.exception.AdminNotFoundException;
import com.capg.onlineflatrental.model.AdminDTO;

public interface IAdminService {

	public AdminDTO addAdmin(Admin admin) throws AdminNotFoundException;
	public AdminDTO updateAdmin(Admin admin) throws AdminNotFoundException;
	public AdminDTO deleteAdmin(int adminId) throws AdminNotFoundException;
	public AdminDTO viewAdmin(int adminId) throws AdminNotFoundException;
	public List<AdminDTO> viewAllAdmin();
	
}