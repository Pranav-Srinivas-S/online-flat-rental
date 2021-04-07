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

import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;
import com.capg.onlineflatrental.service.ILandlordService;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 04-04-2921
 * Description : This is Landlord Controller
*/

@RestController
@RequestMapping("/api/ofr/landlord")
public class LandlordController {

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ILandlordService landlordService;

	@PostMapping("/add-landlord")
	public ResponseEntity<Object> addLandlord(@RequestBody Landlord landlord)
			throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("add-landlord URL is opened");
		LOGGER.info("addLandlord() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		landlordDTO = landlordService.addLandlord(landlord);
		landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addLandlord() has executed");
		return landlordResponse;
	}

	@PutMapping("/update-landlord")
	public ResponseEntity<Object> updateLandlord(@RequestBody Landlord landlord)
			throws LandlordNotFoundException, InvalidFlatInputException {
		LOGGER.info("update-landlord URL is opened");
		LOGGER.info("updateLandlord() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		landlordDTO = landlordService.updateLandlord(landlord);
		landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateLandlord() has executed");
		return landlordResponse;
	}

	@DeleteMapping("/delete-landlord/{id}")
	public ResponseEntity<Object> deleteLandlord(@PathVariable int id) throws LandlordNotFoundException {
		LOGGER.info("delete-landlord URL is opened");
		LOGGER.info("deleteLandlord() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		Optional<LandlordDTO> optional = Optional.of(landlordService.deleteLandlord(id));
		if (optional.isPresent()) {
			landlordDTO = optional.get();
			landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
		} else
			throw new LandlordNotFoundException("No Landlord available in given ID");
		LOGGER.info("deleteLandlord() has executed");
		return landlordResponse;
	}

	@GetMapping("/view-landlord/{id}")
	public ResponseEntity<Object> getLandlordById(@PathVariable int id) throws LandlordNotFoundException {
		LOGGER.info("view-landlord URL is opened");
		LOGGER.info("getLandlordById() is initiated");
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		Optional<LandlordDTO> optional = Optional.of(landlordService.viewLandlord(id));
		if (optional.isPresent()) {
			landlordDTO = optional.get();
			landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
		} else
			throw new LandlordNotFoundException("No Landlord available in given ID");
		LOGGER.info("getLandlordById() has executed");
		return landlordResponse;
	}

	@GetMapping("/view-all-landlords")
	public List<LandlordDTO> getAllLandlords() {
		LOGGER.info("view-all-landlords URL is opened");
		LOGGER.info("getAllLandlords() is initiated");
		LOGGER.info("getAllLandlords() has executed");
		return landlordService.viewAllLandlord();
	}

}
