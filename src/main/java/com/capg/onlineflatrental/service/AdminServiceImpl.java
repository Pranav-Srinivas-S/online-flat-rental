package com.capg.onlineflatrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.exception.AdminNotFoundException;
import com.capg.onlineflatrental.model.AdminDTO;
import com.capg.onlineflatrental.repository.IAdminRepository;
import com.capg.onlineflatrental.util.AdminUtils;



@Service
public class AdminServiceImpl implements IAdminService{
	static String adminNotFound = "No Admin found in given ID";

	@Autowired
	public IAdminRepository adminRepo;
	@Override
	public AdminDTO addAdmin(Admin admin) throws AdminNotFoundException {
		Admin adminEntity;
		if(admin == null)
			adminEntity = null;
		else
			adminEntity = adminRepo.save(admin);
		return AdminUtils.convertToAdminDto(adminEntity);
	}

	@Override
	public AdminDTO updateAdmin(Admin admin) throws AdminNotFoundException {
		Admin adminEntity;
		if(admin == null)
			adminEntity = null;
	    Admin existAdmin = adminRepo.findById(admin.getAdminId()).orElse(null);
		if(existAdmin == null)
			throw new AdminNotFoundException(adminNotFound);
		else
			adminEntity = adminRepo.save(admin);
		return AdminUtils.convertToAdminDto(adminEntity);
	}

	@Override
	public AdminDTO deleteAdmin(int id) throws AdminNotFoundException {
		Admin existAdmin =adminRepo.findById(id).orElse(null);
		if(existAdmin == null)
			throw new AdminNotFoundException(adminNotFound);
		else
			adminRepo.delete(existAdmin);
		return AdminUtils.convertToAdminDto(existAdmin);
	}
	@Override
	public AdminDTO viewAdmin(int id) throws AdminNotFoundException {
		Admin existAdmin = adminRepo.findById(id).orElse(null);
		if(existAdmin == null)
			throw new AdminNotFoundException(adminNotFound);
		return AdminUtils.convertToAdminDto(existAdmin);
	}

	@Override
	public List<AdminDTO> viewAllAdmin() {
		List<Admin> AdminList=adminRepo.findAll();
		return AdminUtils.convertToAdminDtoList(AdminList);
		
	}
	
	
	public static boolean validateAdmin(Admin admin) throws AdminNotFoundException {
		boolean flag= false;
		if(admin == null)
			throw new AdminNotFoundException("Admin details cannot be blank");
		else if (!(validateId(admin.getAdminId()) 
				&& validatePassword(admin.getAdminPassword()) ))
			throw new AdminNotFoundException("Invalid details");
		else 
			flag = true;
	
		return flag;
	}
	
	
	public static boolean validateId(int adminId) throws AdminNotFoundException {
		boolean flag= false;
		if(adminId <= 0)
			throw new AdminNotFoundException("Admin id cannot be 0 or negative");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validatePassword(String adminPassword) throws AdminNotFoundException {
		boolean flag= false;
		if(adminPassword == null)
			throw new AdminNotFoundException("Password cannot be 0 or negative");
		else if(!( adminPassword.matches(".*[@#$%^&+=].*") 
				&& adminPassword.matches(".*[a-z].*") 
				&& adminPassword.matches(".*[A-Z].*") 
				&& adminPassword.matches(".*[0-9].*") 
				&& adminPassword.length() >= 8))
		{
			throw new AdminNotFoundException("Invalid Password");
				
			
		}
		else
			flag=true;
		return flag;
	}

	}
	
	