package com.capg.onlineflatrental.controller;

import java.util.List;

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

import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;
import com.capg.onlineflatrental.service.IFlatBookingService;

/*
 * Author : SHAIK ABDUL BASHEER 
 * Version : 1.0
 * Date : 05-04-2021
 * Description : This is Flat Booking Controller
*/

@RestController
@RequestMapping("/api/ofr/flatbooking")
public class FlatBookingController {

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFlatBookingService flatBookingService;

	@PostMapping("/add-flatBooking")
	public ResponseEntity<Object> addFlatBooking(@RequestBody FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("add-flatBooking URL is opened");
		LOGGER.info("addFlatBooking() is initiated");
		FlatBookingDTO flatBookingDTO = null;
		ResponseEntity<Object> flatBookingResponse = null;
		flatBookingDTO = flatBookingService.addFlatBooking(flatBooking);
		flatBookingResponse = new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addFlatBooking() has executed");
		return flatBookingResponse;
	}

	@PutMapping("/update-flatBooking")
	public ResponseEntity<Object> updateFlatBooking(@RequestBody FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("update-flatBooking URL is opened");
		LOGGER.info("updateFlatBooking() is initiated");
		FlatBookingDTO flatBookingDTO = null;
		ResponseEntity<Object> flatBookingResponse = null;
		flatBookingDTO = flatBookingService.updateFlatBooking(flatBooking);
		flatBookingResponse = new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateFlatBooking() has executed");
		return flatBookingResponse;
	}

	@DeleteMapping("/delete-flatBooking/{id}")
	public ResponseEntity<Object> deleteFlatBooking(@PathVariable int id) throws FlatBookingNotFoundException {
		LOGGER.info("delete-flatBooking URL is opened");
		LOGGER.info("deleteFlatBooking() is initiated");
		FlatBookingDTO flatBookingDTO = flatBookingService.deleteFlatBooking(id);
		LOGGER.info("deleteFlatBooking() has executed");
		return new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/view-flatBooking/{id}")
	public ResponseEntity<Object> getFlatBookingById(@PathVariable int id) throws FlatBookingNotFoundException {
		LOGGER.info("view-flatBooking URL is opened");
		LOGGER.info("getFlatBookingById() is initiated");
		FlatBookingDTO flatBookingDTO = null;
		flatBookingDTO = flatBookingService.viewFlatBooking(id);
		LOGGER.info("getFlatBookingById() has executed");
		return new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/view-all-flatBookings")
	public List<FlatBookingDTO> getAllFlatBooking() {
		LOGGER.info("view-all-flatBooking URL is opened");
		LOGGER.info("getAllFlatBooking() is initiated");
		LOGGER.info("getAllFlatBooking() has executed");
		return flatBookingService.viewAllFlatBooking();
	}

}