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

import com.capg.onlineflatrental.entities.FlatBooking;
import com.capg.onlineflatrental.exception.FlatBookingNotFoundException;
import com.capg.onlineflatrental.model.FlatBookingDTO;
import com.capg.onlineflatrental.service.FlatBookingServiceImpl;

@RestController
@RequestMapping("/api/flatbooking")
public class FlatBookingController {
	@Autowired
	FlatBookingServiceImpl flatbookingService;
	
	@PostMapping("/add-flatbooking")
	public ResponseEntity<Object> addFlatBooking(@RequestBody  FlatBooking flatbooking) throws FlatBookingNotFoundException
	{
		FlatBookingDTO flatbookingDTO = null;
		ResponseEntity<Object> flatbookingResponse = null;
		if(FlatBookingServiceImpl.validateFlatBooking(flatbooking))
		{
			flatbookingDTO = flatbookingService.addFlatBooking(flatbooking);
			flatbookingResponse = new ResponseEntity<Object>(flatbookingDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatBookingNotFoundException("No FlatBooking available in given ID");
		return flatbookingResponse;
	}
@PutMapping("/update-flatbooking")
public ResponseEntity<Object> updateFlatBooking(@RequestBody FlatBooking flatbooking) throws FlatBookingNotFoundException
{
	FlatBookingDTO flatbookingDTO = null;
	ResponseEntity<Object> flatbookingResponse = null;
		if(FlatBookingServiceImpl.validateFlatBoooking(flatbooking))
		{
			flatbookingDTO = flatbookingService.updateFlatBooking(flatbooking);
			flatbookingResponse = new ResponseEntity<Object>(flatbookingDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatBookingNotFoundException("No FlatBooking available in given ID");
		return flatbookingResponse;
	}
@DeleteMapping("/delete-flatbooking/{id}")
public ResponseEntity<Object> deleteFlatBooking(@PathVariable int id) throws FlatBookingNotFoundException
{
	FlatBookingDTO flatbookingDTO = null;
	ResponseEntity<Object> flatbookingResponse = null;
		if(FlatBookingServiceImpl.validateFlatBookingId(id))
		{
			flatbookingDTO = flatbookingService.deleteFlatBooking(id);
			flatbookingResponse = new ResponseEntity<Object>(flatbookingDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatBookingNotFoundException("No FlatBooking available in given ID");
		return flatbookingResponse;
	}

@GetMapping("/view-flatbooking/{id}")
public ResponseEntity<Object> getFlatBookingById(@PathVariable int id) throws FlatBookingNotFoundException
{
	FlatBookingDTO flatbookingDTO = null;
	ResponseEntity<Object> flatbookingResponse = null;
		if(FlatBookingServiceImpl.validateFlatBookingId(id))
		{
			flatbookingDTO = flatbookingService.viewFlatBooking(id);
			flatbookingResponse = new ResponseEntity<Object>(flatbookingDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatBookingNotFoundException("No FlatBooking available in given ID");
		return flatbookingResponse;
	}

@GetMapping("/view-all-flatbookings")
public List<FlatBookingDTO> getAllFlatBooking()
{
	return flatbookingService.viewAllFlatBooking();
}

@ExceptionHandler({FlatBookingNotFoundException.class})
public ResponseEntity<String> handleException()
{
	return new ResponseEntity<String>("FlatBooking Not Found ", HttpStatus.NOT_FOUND);
}
}
