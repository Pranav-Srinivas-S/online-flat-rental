package com.capg.onlineflatrental.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

/*
 * Author : PRANAV SRINIVAS S
 * Version : 1.0
 * Date : 04-04-2021
 * Description : This is Tenant Controller.
*/

@RestController
@RequestMapping("/api/ofr")
@CrossOrigin(origins="http://localhost:3000")
public class TenantController {

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITenantService tenantService;
	/************************************************************************************
	 * Method: addtenant 
	 * Description: It is used to add tenant into tenant_details table
	 * @param tenant: Tenant Object
	 * @returns tenant It returns ResponseEntity with TenantDTO Object.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception: TenantNotFoundException
	 * Created By- PRANAV SRINIVAS S
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@PostMapping("/add-tenant")
	public ResponseEntity<Object> addTenant(@RequestBody Tenant tenant) throws TenantNotFoundException {
		LOGGER.info("add-tenant URL is opened");
		LOGGER.info("addTenant() is initiated");
		System.out.println("tenant :"+tenant);
		TenantDTO tenantDTO = tenantService.addTenant(tenant);
		LOGGER.info("addTenant() has executed");
		return new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
	}
	
	/************************************************************************************
	 * Method: upadatetenant 
	 * Description: It is used to update tenant into tenant_details table
	 * @param tenant: Tenant Object
	 * @returns tenant It returns TenantDTO Object with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @exception: TenantNotFoundException
	 * *Created By- PRANAV SRINIVAS S
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@PutMapping("/update-tenant")
	public ResponseEntity<Object> updateTenant(@RequestBody Tenant tenant) throws TenantNotFoundException {
		LOGGER.info("update-tenant URL is opened");
		LOGGER.info("updateTenant() is initiated");
		TenantDTO tenantDTO = tenantService.updateTenant(tenant);
		LOGGER.info("updateTenant() has executed");
		return new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
	}
	
	/************************************************************************************
	 * Method: deletetenant
	 * Description: It is used to remove tenant from tenant table
	 * @param id: int id
	 * @returns tenant It returns TenantDTO Object with details
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable: int id
	 * @exception: TenantNotFoundException
	 * Created By- PRANAV SRINIVAS S
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@DeleteMapping("/delete-tenant/{id}")
	public ResponseEntity<Object> deleteTenant(@PathVariable int id) throws TenantNotFoundException {
		LOGGER.info("delete-tenant URL is opened");
		LOGGER.info("deleteTenant() is initiated");
		TenantDTO tenantDTO = tenantService.deleteTenant(id);
		LOGGER.info("deleteTenant() has executed");
		return new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
	}
	
	/************************************************************************************
	 * Method: viewtenant
	 * Description: It is used to view tenant from tenant table
	 * @param tenant: int id
	 * @returns tenant It returns TenantDTO Object with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @PathVariable: int id
	 * @exception: TenantNotFoundException
	 * Created By- PRANAV SRINIVAS S
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/view-tenant/{id}")
	public ResponseEntity<Object> getTenantById(@PathVariable int id) throws TenantNotFoundException {
		LOGGER.info("view-tenant URL is opened");
		LOGGER.info("getTenantById() is initiated");
		TenantDTO tenantDTO = tenantService.viewTenant(id);
		LOGGER.info("getTenantById() has executed");
		return new ResponseEntity<Object>(tenantDTO, HttpStatus.ACCEPTED);
	}

	/************************************************************************************
	 * Method: viewAlltenants
	 * Description: It is used to view all tenant details present in tenant table
	 * @returns tenant It returns all List<TenantDTO> Object with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * Created By- PRANAV SRINIVAS S
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/view-all-tenants")
	public List<TenantDTO> getAllTenants() {
		LOGGER.info("view-all-tenants URL is opened");
		LOGGER.info("getAllTenant() is initiated");
		LOGGER.info("getAllTenant() has executed");
		return tenantService.viewAllTenant();
	}

}
