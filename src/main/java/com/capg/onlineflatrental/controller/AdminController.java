package com.capg.onlineflatrental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.exception.AdminNotFoundException;
import com.capg.onlineflatrental.model.AdminDTO;
import com.capg.onlineflatrental.service.AdminServiceImpl;
import com.capg.onlineflatrental.service.IAdminService;

@RestController
@RequestMapping("/api/ofr/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	@PostMapping("/add-admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Admin admin) throws AdminNotFoundException
	{
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		if(AdminServiceImpl.validateAdmin(admin))
		{
			adminDTO = adminService.addAdmin(admin);
			adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new AdminNotFoundException("No Admin available in given ID");
		return adminResponse;
	}
	
	@PutMapping("/update-admin")
	public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException
	{
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		if(AdminServiceImpl.validateAdmin(admin) && AdminServiceImpl.validateId(admin.getAdminId()))
			{
			adminDTO = adminService.updateAdmin(admin);
			adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
			}
			else
				throw new AdminNotFoundException("No Admin available in given ID");
		return adminResponse;
	}
	
	@DeleteMapping("/delete-admin/{adminId}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable int adminId) throws AdminNotFoundException
	{
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		Optional<AdminDTO> optional = Optional.of(adminService.deleteAdmin(adminId));
		if(optional.isPresent())
		{
			adminDTO = optional.get();
			adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		}
			else
				throw new AdminNotFoundException("No Admin available in given ID");
		return adminResponse;
	}
	
	@GetMapping("/view-admin/{adminId}")
	public ResponseEntity<Object> getAdminById(@PathVariable int adminId) throws AdminNotFoundException
	{		
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		Optional<AdminDTO> optional = Optional.of(adminService.viewAdmin(adminId));
		if(optional.isPresent())
		{
			adminDTO = optional.get();
			adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new AdminNotFoundException("No Admin available in given ID");
		return adminResponse;
	}
	
	@GetMapping("/view-all-admin")
	public List<AdminDTO> getAllAdmin()
	{
		return adminService.viewAllAdmin();
	}
	

	
}
