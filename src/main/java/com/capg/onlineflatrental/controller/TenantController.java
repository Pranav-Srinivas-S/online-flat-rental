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

import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.TenantDTO;
import com.capg.onlineflatrental.service.ITenantService;
import com.capg.onlineflatrental.service.TenantServiceImpl;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

	@Autowired
	ITenantService tenantService;
	
	@Autowired
	TenantServiceImpl service;
	
	@PostMapping("/add-tenant")
	public ResponseEntity<Object> addTenant(@RequestBody Tenant tenant) throws TenantNotFoundException
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		if(TenantServiceImpl.validateTenant(tenant))
		{
			tenantDTO = tenantService.addTenant(tenant);
			tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new TenantNotFoundException("Invalid Tenant Details");
		return tenantResponse;
	}
	
	@PutMapping("/update-tenant")
	public ResponseEntity<Object> updateTenant(@RequestBody Tenant tenant) throws TenantNotFoundException
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		if(TenantServiceImpl.validateTenant(tenant) && service.validateTenantId(tenant.getTenantId()))
		{
			tenantDTO = tenantService.updateTenant(tenant);
			tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new TenantNotFoundException("No Tenant available in given ID");
		return tenantResponse;
	}
	
	@DeleteMapping("/delete-tenant/{id}")
	public ResponseEntity<Object> deleteTenant(@PathVariable int id) throws TenantNotFoundException
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		Optional<TenantDTO> optional = Optional.of(tenantService.deleteTenant(id));
		if(optional.isPresent())
		{
			tenantDTO = optional.get();
			tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new TenantNotFoundException("No Tenant available in given ID");
		return tenantResponse;
	}
	
	@GetMapping("/view-tenant/{id}")
	public ResponseEntity<Object> getTenantById(@PathVariable int id) throws TenantNotFoundException
	{
		TenantDTO tenantDTO = null;
		ResponseEntity<Object> tenantResponse = null;
		Optional<TenantDTO> optional = Optional.of(tenantService.viewTenant(id));
		if(optional.isPresent())
		{
			tenantDTO = optional.get();
			tenantResponse = new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new TenantNotFoundException("No Tenant available in given ID");
		return tenantResponse;
	}
	
	@GetMapping("/view-all-tenants")
	public List<TenantDTO> getAllTenants()
	{
		return tenantService.viewAllTenant();
	}
	
//	@ExceptionHandler({TenantNotFoundException.class})
//	public ResponseEntity<String> handleException()
//	{
//		return new ResponseEntity<String>("Tenant Not Found ", HttpStatus.NOT_FOUND);
//	}

}
