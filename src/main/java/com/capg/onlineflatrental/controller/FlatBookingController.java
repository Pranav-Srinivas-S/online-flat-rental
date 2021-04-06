package com.capg.onlineflatrental.controller;

import java.util.List;

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
import com.capg.onlineflatrental.service.FlatBookingServiceImpl;
import com.capg.onlineflatrental.service.IFlatBookingService;

@RestController
@RequestMapping("/api/ofr/flatbooking")
public class FlatBookingController {
	
	@Autowired
	IFlatBookingService flatBookingService;
	
	@PostMapping("/add-flatbooking")
	public ResponseEntity<Object> addFlatBooking(@RequestBody  FlatBooking flatBooking) throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException
	{
		FlatBookingDTO flatBookingDTO = null;
		ResponseEntity<Object> flatBookingResponse = null;
		if(FlatBookingServiceImpl.validateFlatBooking(flatBooking))
		{
			flatBookingDTO = flatBookingService.addFlatBooking(flatBooking);
			flatBookingResponse = new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatBookingNotFoundException("No FlatBooking available in given ID");
		return flatBookingResponse;
	}
	
	@PutMapping("/update-flatbooking")
	public ResponseEntity<Object> updateFlatBooking(@RequestBody FlatBooking flatBooking) throws FlatBookingNotFoundException, TenantNotFoundException, InvalidFlatInputException
{
	FlatBookingDTO flatBookingDTO = null;
	ResponseEntity<Object> flatBookingResponse = null;
		if(FlatBookingServiceImpl.validateFlatBooking(flatBooking))
		{
			flatBookingDTO = flatBookingService.updateFlatBooking(flatBooking);
			flatBookingResponse = new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatBookingNotFoundException("No FlatBooking available in given ID");
		return flatBookingResponse;
	}
	
	@DeleteMapping("/delete-flatbooking/{id}")
	public ResponseEntity<Object> deleteFlatBooking(@PathVariable int id) throws FlatBookingNotFoundException
{
		FlatBookingDTO flatBookingDTO = flatBookingService.deleteFlatBooking(id);
		return new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/view-flatbooking/{id}")
	public ResponseEntity<Object> getFlatBookingById(@PathVariable int id) throws FlatBookingNotFoundException
{
		FlatBookingDTO flatBookingDTO = null;
		flatBookingDTO = flatBookingService.viewFlatBooking(id);
		return new ResponseEntity<Object>(flatBookingDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/view-all-flatbookings")
	public List<FlatBookingDTO> getAllFlatBooking()
{
	return flatBookingService.viewAllFlatBooking();
}

//	@ExceptionHandler({FlatBookingNotFoundException.class})
//	public ResponseEntity<String> handleException()
//{
//	return new ResponseEntity<String>("FlatBooking Not Found ", HttpStatus.NOT_FOUND);
//}
	
}
