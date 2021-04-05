package com.capg.onlineflatrental.controller;


import java.util.List;
import java.util.Optional;
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
import com.capg.onlineflatrental.service.FlatServiceImpl;

@RestController
@RequestMapping("/api/flat")
public class FlatController {

	@Autowired
	FlatServiceImpl flatService;
	
	@PostMapping("/add-flat")
	public ResponseEntity<Object> addFlat(@RequestBody Flat flat) throws InvalidFlatInputException
	{
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		if(FlatServiceImpl.validateFlat(flat))
		{
			flatDTO = flatService.addFlat(flat);
			flatResponse = new ResponseEntity<Object>(flatDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new InvalidFlatInputException("Invalid inputs for adding flat details");
		return flatResponse;
	}
	
	@PutMapping("/update-flat")
	public ResponseEntity<Object> updateFlat(@RequestBody Flat flat) throws  FlatNotFoundException, InvalidFlatInputException
	{
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		if(FlatServiceImpl.validateFlat(flat))
		{
			flatDTO = flatService.updateFlat(flat);
			flatResponse = new ResponseEntity<Object>(flatDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatNotFoundException("No Flat available in given ID");
		return flatResponse;
	}
	
	@DeleteMapping("/delete-flat/{id}")
	public ResponseEntity<Object> deleteFlat(@PathVariable int id) throws FlatNotFoundException
	{
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		Optional<FlatDTO> optional = Optional.of(flatService.deleteFlat(id));
		if(optional.isPresent())
		{
			flatDTO = optional.get();
			flatResponse = new ResponseEntity<Object>(flatDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatNotFoundException("No Flat available in given ID");
		return flatResponse;
	}
	
	@GetMapping("/view-flat/{id}")
	public ResponseEntity<Object> getFlatById(@PathVariable int id) throws FlatNotFoundException
	{
		FlatDTO flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		Optional<FlatDTO> optional = Optional.of(flatService.viewFlat(id));
		if(optional.isPresent())
		{
			flatDTO = optional.get();
			flatResponse = new ResponseEntity<Object>(flatDTO, HttpStatus.ACCEPTED);
		}
		else
			throw new FlatNotFoundException("No flat available in given ID");
		return flatResponse;
	}
	
	@GetMapping("/view-all-flats")
	public List<FlatDTO> getAllFlats()
	{
		return flatService.viewAllFlat();
	}
	
	@GetMapping("/view-flat-by-cost/{cost},{availability}")
	public ResponseEntity<Object> getFlatByCost(@PathVariable float cost, @PathVariable String availability) throws FlatNotFoundException, InvalidFlatInputException
	{
		List<FlatDTO> flatDTO = null;
		ResponseEntity<Object> flatResponse = null;
		Optional<List<FlatDTO>> optional = Optional.of(flatService.viewAllFlatByCost(cost,availability));
		if(FlatServiceImpl.validateFlatCost(cost) && FlatServiceImpl.validateFlatAvailability(availability))
		{
			if(optional.isPresent())
		{
			flatDTO = optional.get();
			flatResponse = new ResponseEntity<Object>(flatDTO, HttpStatus.ACCEPTED);
		}
		    else
			throw new FlatNotFoundException("No flat available for given cost");
		}
		else
			throw new InvalidFlatInputException(" Invalid input for cost/availability");
		return flatResponse;
	}
}