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
import com.capg.onlineflatrental.entities.Landlord;
import com.capg.onlineflatrental.exception.LandlordNotFoundException;
import com.capg.onlineflatrental.model.LandlordDTO;
import com.capg.onlineflatrental.service.LandlordServiceImpl;

@RestController
@RequestMapping("/api/landlord")
public class LandlordController {

	@Autowired
	LandlordServiceImpl landlordService;
	
	@PostMapping("/add-landlord")
	public ResponseEntity<Object> addLandlord(@RequestBody Landlord landlord)
	{
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		try {
			if(LandlordServiceImpl.validateLandlord(landlord))
			{
				landlordDTO = landlordService.addLandlord(landlord);
				landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
			}
			else
				landlordResponse = new ResponseEntity<Object>("Landlord Insertion Failed", HttpStatus.BAD_REQUEST);
		} catch (LandlordNotFoundException e) {
			e.printStackTrace();
		}
		return landlordResponse;
	}
	
	@PutMapping("/update-landlord")
	public ResponseEntity<Object> updateLandlord(@RequestBody Landlord landlord)
	{
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		try {
			if(LandlordServiceImpl.validateLandlord(landlord))
			{
				landlordDTO = landlordService.updateLandlord(landlord);
				landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
			}
			else
				landlordResponse = new ResponseEntity<Object>("Landlord Updation Failed", HttpStatus.BAD_REQUEST);
		} catch (LandlordNotFoundException e) {
			e.printStackTrace();
		}
		return landlordResponse;
	}
	
	@DeleteMapping("/delete-landlord/{id}")
	public ResponseEntity<Object> deleteTenant(@PathVariable int id)
	{
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		try {
			if(landlordService.validateLandlordId(id))
			{
				landlordDTO = landlordService.deleteLandlord(id);
				landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
			}
			else
				landlordResponse = new ResponseEntity<Object>("Landlord Deletion Failed", HttpStatus.BAD_REQUEST);
		} catch (LandlordNotFoundException e) {
			e.printStackTrace();
		}
		return landlordResponse;
	}
	
	@GetMapping("/view-landlord/{id}")
	public ResponseEntity<Object> getLandlordById(@PathVariable int id)
	{
		LandlordDTO landlordDTO = null;
		ResponseEntity<Object> landlordResponse = null;
		try {
			if(landlordService.validateLandlordId(id))
			{
				landlordDTO = landlordService.viewLandlord(id);
				landlordResponse = new ResponseEntity<Object>(landlordDTO, HttpStatus.ACCEPTED);
			}
			else
				landlordResponse = new ResponseEntity<Object>("No Landlord available in given ID", HttpStatus.BAD_REQUEST);
		} catch (LandlordNotFoundException e) {
			e.printStackTrace();
		}
		return landlordResponse;
	}
	
	@GetMapping("/view-all-landlords")
	public List<LandlordDTO> getAllLandlords()
	{
		return landlordService.viewAllLandlord();
	}
	
	@ExceptionHandler({LandlordNotFoundException.class})
	public ResponseEntity<String> handleException()
	{
		return new ResponseEntity<String>("Employee Not Found ", HttpStatus.NOT_FOUND);
	}
	
}
