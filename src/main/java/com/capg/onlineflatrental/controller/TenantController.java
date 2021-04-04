package com.capg.onlineflatrental.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.TenantDTO;
import com.capg.onlineflatrental.service.TenantServiceImpl;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

	@Autowired
	TenantServiceImpl tenantService;
	
	@PostMapping("/add-tenant")
	public ResponseEntity<Object> addTenant(@RequestBody Tenant tenant)
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		try {
			if(TenantServiceImpl.validateTenant(tenant))
			{
				tenantDTO = tenantService.addTenant(tenant);
				tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
			}
			else
				tenantResponse = new ResponseEntity<Object>("Tenant Insertion Failed", HttpStatus.BAD_REQUEST);
		} catch (TenantNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tenantResponse;
	}
	
	@PutMapping("/update-tenant")
	public ResponseEntity<Object> updateTenant(@RequestBody Tenant tenant)
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		try {
			if(TenantServiceImpl.validateTenant(tenant))
			{
				tenantDTO = tenantService.updateTenant(tenant);
				tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
			}
			else
				tenantResponse = new ResponseEntity<Object>("Tenant Updation Failed", HttpStatus.BAD_REQUEST);
		} catch (TenantNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tenantResponse;
	}
	
	@DeleteMapping("/delete-tenant/{id}")
	public ResponseEntity<Object> deleteTenant(@PathVariable int id)
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		try {
			if(tenantService.validateTenantId(id))
			{
				tenantDTO = tenantService.deleteTenant(id);
				tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
			}
			else
				tenantResponse = new ResponseEntity<Object>("Tenant Deletion Failed", HttpStatus.BAD_REQUEST);
		} catch (TenantNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tenantResponse;
	}
	
	@GetMapping("/view-tenant/{id}")
	public ResponseEntity<Object> getTenantById(@PathVariable int id)
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		try {
			if(tenantService.validateTenantId(id))
			{
				tenantDTO = tenantService.viewTenant(id);
				tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
			}
			else
				tenantResponse = new ResponseEntity<Object>("No Tenant available in given ID", HttpStatus.BAD_REQUEST);
		} catch (TenantNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tenantResponse;
	}
	
	@GetMapping("/view-all-tenants")
	public List<TenantDTO> getAllTenants()
	{
		return tenantService.viewAllTenant();
	}
	
	@ExceptionHandler({TenantNotFoundException.class})
	public ResponseEntity<String> handleException()
	{
		return new ResponseEntity<String>("Employee Not Found ", HttpStatus.NOT_FOUND);
	}
	
}
