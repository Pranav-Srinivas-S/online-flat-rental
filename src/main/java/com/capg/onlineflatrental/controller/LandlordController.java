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

import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;
import com.capg.onlineflatrental.service.ILandlordService;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 04-04-2021
 * Description : This is Landlord Controller
*/

@RestController
@RequestMapping("/api/ofr/landlord")
@CrossOrigin(origins="http://localhost:3000")
public class LandlordController {

	static final Logger LOGGER = LoggerFactory.getLogger(LandlordController.class);

	@Autowired
	private ILandlordService landlordService;
	
	/************************************************************************************
	 * Method: addlandlord 
	 * Description: It is used to add landlord into landlords table
	 * @param landlord: landlord's reference variable.
	 * @returns landlord It returns landlord with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@PostMapping("/add-landlord")
	public ResponseEntity<Object> addLandlord(@RequestBody Landlord landlord)
			throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("add-landlord URL is opened");
		LOGGER.info("addLandlord() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		landlordDTO = landlordService.addLandlord(landlord);
		landlordResponse = new ResponseEntity<>(landlordDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addLandlord() has executed");
		return landlordResponse;
	}

	/************************************************************************************
	 * Method: updatelandlord 
	 * Description: It is used to update landlord into landlords table
	 * @param landlord: landlord's reference variable.
	 * @returns landlord It returns landlord with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@PutMapping("/update-landlord")
	public ResponseEntity<Object> updateLandlord(@RequestBody Landlord landlord)
			throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("update-landlord URL is opened");
		LOGGER.info("updateLandlord() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		landlordDTO = landlordService.updateLandlord(landlord);
		landlordResponse = new ResponseEntity<>(landlordDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateLandlord() has executed");
		return landlordResponse;
	}
	
	/************************************************************************************
	 * Method: Deletelandlord 
	 * Description: It is used to remove landlord from landlords table
	 * @param landlord: int id
	 * @returns landlord It returns landlord with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@DeleteMapping("/delete-landlord/{id}")
	public ResponseEntity<Object> deleteLandlord(@PathVariable int id) throws LandlordNotFoundException {
		LOGGER.info("delete-landlord URL is opened");
		LOGGER.info("deleteLandlord() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		landlordDTO = landlordService.deleteLandlord(id);
		landlordResponse = new ResponseEntity<>(landlordDTO, HttpStatus.ACCEPTED);
		LOGGER.info("deleteLandlord() has executed");
		return landlordResponse;
	}
	
	/************************************************************************************
	 * Method: getlandlordById 
	 * Description: It is used to view landlord by id from landlords table
	 * @param landlord: int id
	 * @returns landlord It returns landlord with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/view-landlord/{id}")
	public ResponseEntity<Object> getLandlordById(@PathVariable int id) throws LandlordNotFoundException {
		LOGGER.info("view-landlord URL is opened");
		LOGGER.info("getLandlordById() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		landlordDTO = landlordService.viewLandlord(id);
		landlordResponse = new ResponseEntity<>(landlordDTO, HttpStatus.ACCEPTED);
		LOGGER.info("getLandlordById() has executed");
		return landlordResponse;
	}

	/************************************************************************************
	 * Method: getAlllandlords 
	 * Description: It is used to view all landlords in landlords table
	 * @returns landlord It returns landlord with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-NITHISHA K A
     *Created Date -  04-04-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/view-all-landlords")
	public List<LandlordDTO> getAllLandlords() {
		LOGGER.info("view-all-landlords URL is opened");
		LOGGER.info("getAllLandlords() is initiated");
		LOGGER.info("getAllLandlords() has executed");
		return landlordService.viewAllLandlord();
	}

}
