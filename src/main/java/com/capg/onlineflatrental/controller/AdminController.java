package com.capg.onlineflatrental.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.capg.onlineflatrental.service.IAdminService;

/*************************************************************************************
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 06-04-2021
 * Description : This is Admin Controller
*************************************************************************************/

@RestController
@RequestMapping("/api/ofr/admin")
public class AdminController {

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IAdminService adminService;
	
	/************************************************************************************
	 * Method: addadmin 
	 * Description: It is used to add admin into admin_details table
	 * @param admin: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  NITHISHA K A 
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	@PostMapping("/add-admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Admin admin) throws AdminNotFoundException {
		LOGGER.info("add-admin URL is opened");
		LOGGER.info("addAdmin() is initiated");
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		adminDTO = adminService.addAdmin(admin);
		adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addAdmin() has executed");
		return adminResponse;
	}
	
	/************************************************************************************
	 * Method: updateadmin 
	 * Description: It is used to update admin into admin_details table
	 * @param admin: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * *Created By-  NITHISHA K A 
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@PutMapping("/update-admin")
	public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException {
		LOGGER.info("update-admin URL is opened");
		LOGGER.info("updateAdmin() is initiated");
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		adminDTO = adminService.updateAdmin(admin);
		adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateAdmin() has executed");
		return adminResponse;
	}

	/************************************************************************************
	 * Method: deleteadmin
	 * Description: It is used to remove admin into admin_details table
	 * @param id: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  NITHISHA K A 
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@DeleteMapping("/delete-admin/{adminId}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable int adminId) throws AdminNotFoundException {
		LOGGER.info("delete-admin URL is opened");
		LOGGER.info("deleteAdmin() is initiated");
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		Optional<AdminDTO> optional = Optional.of(adminService.deleteAdmin(adminId));
		if (optional.isPresent()) {
			adminDTO = optional.get();
			adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		} else
			throw new AdminNotFoundException("No Admin available in given ID");
		LOGGER.info("deleteAdmin() has executed");
		return adminResponse;
	}
	
	/************************************************************************************
	 * Method: viewadmin
	 * Description: It is used to view admin into admin_details table
	 * @param admin: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  NITHISHA K A 
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/


	@GetMapping("/view-admin/{adminId}")
	public ResponseEntity<Object> getAdminById(@PathVariable int adminId) throws AdminNotFoundException {
		LOGGER.info("view-admin URL is opened");
		LOGGER.info("getAdminById() is initiated");
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		Optional<AdminDTO> optional = Optional.of(adminService.viewAdmin(adminId));
		if (optional.isPresent()) {
			adminDTO = optional.get();
			adminResponse = new ResponseEntity<Object>(adminDTO, HttpStatus.ACCEPTED);
		} else
			throw new AdminNotFoundException("No Admin available in given ID");
		LOGGER.info("getAdminById() has executed");
		return adminResponse;
	}
	
	/************************************************************************************
	 * Method: viewAlladmins
	 * Description: It is used to view all admin details present admin_details table
	 * @param admin: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  NITHISHA K A 
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/view-all-admin")
	public List<AdminDTO> getAllAdmin() {
		LOGGER.info("view-add-admin URL is opened");
		LOGGER.info("getAllAdmin() is initiated");
		LOGGER.info("getAllAdmin() has executed");
		return adminService.viewAllAdmin();
	}

}
