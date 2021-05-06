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

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/ofr")
public class FlatBookingController {

	static final Logger LOGGER = LoggerFactory.getLogger(FlatBooking.class);

	@Autowired
	private IFlatBookingService flatBookingService;
	
	/************************************************************************************
	 * Method: addFlatBooking
	 * Description: It is used to add flatBooking into flatbooking_details table
	 * @param flatbooking: FlatBooking reference variable.
	 * @returns flatBooking :It returns flatBooking with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  SHAIK ABDUL BASHEER 
     *Created Date -05-04-2021
	 * 
	 ************************************************************************************/


	@PostMapping("/add-flatBooking")
	public ResponseEntity<Object> addFlatBooking(@RequestBody FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("add-flatBooking URL is opened");
		LOGGER.info("addFlatBooking() is initiated");
		System.out.println(flatBooking);
		FlatBookingDTO flatBookingDTO = null;
		ResponseEntity<Object> flatBookingResponse = null;
		flatBookingDTO = flatBookingService.addFlatBooking(flatBooking);
		flatBookingResponse = new ResponseEntity<>(flatBookingDTO, HttpStatus.ACCEPTED);
		LOGGER.info("addFlatBooking() has executed");
		return flatBookingResponse;
	}
	
	/************************************************************************************
	 * Method: updateFlatBooking
	 * Description: It is used to update flatBooking into flatbooking_details table
	 * @param flatbooking: FlatBooking reference variable.
	 * @returns flatBooking: It returns flatBooking with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * *Created By-  SHAIK ABDUL BASHEER 
     *Created Date -  05-04-2021
	 * 
	 ************************************************************************************/
	@PutMapping("/update-flatBooking")
	public ResponseEntity<Object> updateFlatBooking(@RequestBody FlatBooking flatBooking)
			throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException {
		LOGGER.info("update-flatBooking URL is opened");
		LOGGER.info("updateFlatBooking() is initiated");
		FlatBookingDTO flatBookingDTO = null;
		ResponseEntity<Object> flatBookingResponse = null;
		flatBookingDTO = flatBookingService.updateFlatBooking(flatBooking);
		flatBookingResponse = new ResponseEntity<>(flatBookingDTO, HttpStatus.ACCEPTED);
		LOGGER.info("updateFlatBooking() has executed");
		return flatBookingResponse;
	}
	/************************************************************************************
	 * Method: DeleteFlatBooking
	 * Description: It is used to Delete flatBooking into flatbooking_details table
	 * @param id: FlatBooking reference variable.
	 * @returns flatBooking: It returns FlatBooking with details
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  SHAIK ABDUL BASHEER 
     *Created Date -  05-04-2021
	 * 
	 ************************************************************************************/
	@DeleteMapping("/delete-flatBooking/{id}")
	public ResponseEntity<Object> deleteFlatBooking(@PathVariable int id) throws FlatBookingNotFoundException {
		LOGGER.info("delete-flatBooking URL is opened");
		LOGGER.info("deleteFlatBooking() is initiated");
		FlatBookingDTO flatBookingDTO = flatBookingService.deleteFlatBooking(id);
		LOGGER.info("deleteFlatBooking() has executed");
		return new ResponseEntity<>(flatBookingDTO, HttpStatus.ACCEPTED);
	}
	/************************************************************************************
	 * Method: viewFlatBooking
	 * Description: It is used to view FlatBooking into flatbooking_details table
	 * @param flatbooking: flatBooking reference variable.
	 * @returns flatBooking: It returns flatBooking with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- SHAIK ABDUL BASHEER 
     *Created Date - 05-04-2021  
	 * 
	 ************************************************************************************/
	@GetMapping("/view-flatBooking/{id}")
	public ResponseEntity<Object> getFlatBookingById(@PathVariable int id) throws FlatBookingNotFoundException {
		LOGGER.info("view-flatBooking URL is opened");
		LOGGER.info("getFlatBookingById() is initiated");
		FlatBookingDTO flatBookingDTO = null;
		flatBookingDTO = flatBookingService.viewFlatBooking(id);
		LOGGER.info("getFlatBookingById() has executed");
		return new ResponseEntity<>(flatBookingDTO, HttpStatus.ACCEPTED);
	}
	/************************************************************************************
	 * Method: viewAllFlatBookings
	 * Description: It is used to view all FlatBooking details present flatbooking_details table
	 * @param flatbooking: FlatBooking reference variable.
	 * @returns flatBooking:It returns flatBooking with details
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-  SHAIK ABDUL BASHEER 
     *Created Date - 05-04-2021  
	 * 
	 ************************************************************************************/

	@GetMapping("/view-all-flatBookings")
	public List<FlatBookingDTO> getAllFlatBooking() {
		LOGGER.info("view-all-flatBooking URL is opened");
		LOGGER.info("getAllFlatBooking() is initiated");
		LOGGER.info("getAllFlatBooking() has executed");
		return flatBookingService.viewAllFlatBooking();
	}

}
