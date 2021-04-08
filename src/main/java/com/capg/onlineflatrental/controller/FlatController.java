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

import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.exception.FlatNotFoundException;
import com.capg.onlineflatrental.exception.InvalidFlatInputException;
import com.capg.onlineflatrental.model.FlatDTO;
import com.capg.onlineflatrental.service.IFlatService;

/*
 * Author : AJITHKUMAR A 
 * Version : 1.0
 * Date : 05-04-2021
 * Description : This is Flat Controller
*/

@RestController
@RequestMapping("/api/ofr/flat")
public class FlatController {

	static final Logger LOGGER = LoggerFactory.getLogger(FlatController.class);

	@Autowired
	private IFlatService flatService;

	@PostMapping("/add-flat")
	public ResponseEntity<Object> addFlat(@RequestBody Flat flat) throws InvalidFlatInputException {
		LOGGER.info("add-flat URL is opened");
		LOGGER.info("addFlat() is initiated");
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		flatDTO = flatService.addFlat(flat);
		flatResponse = new ResponseEntity<>(flatDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addFlat() has executed");
		return flatResponse;
	}

	@PutMapping("/update-flat")
	public ResponseEntity<Object> updateFlat(@RequestBody Flat flat)
			throws FlatNotFoundException, InvalidFlatInputException {
		LOGGER.info("update-flat URL is opened");
		LOGGER.info("updateFlat() is initiated");
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		flatDTO = flatService.updateFlat(flat);
		flatResponse = new ResponseEntity<>(flatDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateFlat() has executed");
		return flatResponse;
	}

	@DeleteMapping("/delete-flat/{id}")
	public ResponseEntity<Object> deleteFlat(@PathVariable int id) throws FlatNotFoundException {
		LOGGER.info("delete-flat URL is opened");
		LOGGER.info("deleteFlat() is initiated");
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		flatDTO = flatService.deleteFlat(id);
		flatResponse = new ResponseEntity<>(flatDTO, HttpStatus.ACCEPTED);
		LOGGER.info("deleteFlat() has executed");
		return flatResponse;
	}

	@GetMapping("/view-flat/{id}")
	public ResponseEntity<Object> getFlatById(@PathVariable int id) throws FlatNotFoundException {
		LOGGER.info("view-flat URL is opened");
		LOGGER.info("getFlatById() is initiated");
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		flatDTO = flatService.viewFlat(id);
		flatResponse = new ResponseEntity<>(flatDTO, HttpStatus.ACCEPTED);
		LOGGER.info("getFlatById() has executed");
		return flatResponse;
	}

	@GetMapping("/view-all-flats")
	public List<FlatDTO> getAllFlats() {
		LOGGER.info("view-all-flat URL is opened");
		LOGGER.info("getAllFlats() is initiated");
		LOGGER.info("getAllFlats() has executed");
		return flatService.viewAllFlat();
	}

	@GetMapping("/view-flat-by-cost/{cost},{availability}")
	public ResponseEntity<Object> getFlatByCost(@PathVariable float cost, @PathVariable String availability)
			throws FlatNotFoundException, InvalidFlatInputException {
		LOGGER.info("view-flat-by-cost URL is opened");
		LOGGER.info("getFlatsByCost() is initiated");
		List<FlatDTO> flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		flatDTO = flatService.viewAllFlatByCost(cost, availability);
		flatResponse = new ResponseEntity<>(flatDTO, HttpStatus.ACCEPTED);
		LOGGER.info("getFlatsByCost() has executed");
		return flatResponse;
	}

}