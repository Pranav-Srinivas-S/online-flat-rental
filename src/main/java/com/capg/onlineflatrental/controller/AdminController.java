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
import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.exception.AdminNotFoundException;
import com.capg.onlineflatrental.model.AdminDTO;
import com.capg.onlineflatrental.service.IAdminService;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 06-04-2021
 * Description : This is Admin Controller
*/

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/ofr")
public class AdminController {

	static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private IAdminService adminService;
	
	/************************************************************************************
	 * Method: addadmin 
	 * Description: It is used to add admin into admins table
	 * @param admin: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
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
		adminResponse = new ResponseEntity<>(adminDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addAdmin() has executed");
		return adminResponse;
	}
	
	/************************************************************************************
	 * Method: updateadmin 
	 * Description: It is used to update admin into admins table
	 * @param admin: admin's reference variable.
	 * @returns admin It returns admin with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
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
		adminResponse = new ResponseEntity<>(adminDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateAdmin() has executed");
		return adminResponse;
	}
	
	/************************************************************************************
	 * Method: Deleteadmin 
	 * Description: It is used to remove admin from admins table
	 * @param admin: int id
	 * @returns admin It returns admin with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@DeleteMapping("/delete-admin/{adminId}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable int adminId) throws AdminNotFoundException {
		LOGGER.info("delete-admin URL is opened");
		LOGGER.info("deleteAdmin() is initiated");
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		adminDTO = adminService.deleteAdmin(adminId);
		adminResponse = new ResponseEntity<>(adminDTO, HttpStatus.ACCEPTED);
		LOGGER.info("deleteAdmin() has executed");
		return adminResponse;
	}
	
	/************************************************************************************
	 * Method: getadminById 
	 * Description: It is used to view admin by id from admins table
	 * @param admin: int id
	 * @returns admin It returns admin with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/view-admin/{adminId}")
	public ResponseEntity<Object> getAdminById(@PathVariable int adminId) throws AdminNotFoundException {
		LOGGER.info("view-admin URL is opened");
		LOGGER.info("getAdminById() is initiated");
		AdminDTO adminDTO = null;
		ResponseEntity<Object> adminResponse = null;
		adminDTO = adminService.viewAdmin(adminId);
		adminResponse = new ResponseEntity<>(adminDTO, HttpStatus.ACCEPTED);
		LOGGER.info("getAdminById() has executed");
		return adminResponse;
	}

	/************************************************************************************
	 * Method: getAlladmins 
	 * Description: It is used to view all admins in admins table
	 * @returns admin It returns admin with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
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
