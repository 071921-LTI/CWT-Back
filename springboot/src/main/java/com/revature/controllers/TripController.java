package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Trip;
import com.revature.services.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {
	private TripService ts;
	
	@Autowired
	public TripController(TripService ts) {
		super();
		this.ts = ts;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Trip> getTripById(@PathVariable("id") int id){
		return new ResponseEntity<Trip>(ts.getTripById(id), HttpStatus.OK);
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Trip>> getTrips(){
		return new ResponseEntity<>(ts.getAllTrips(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createTrip(@Valid @RequestBody Trip newTrip){
		if(ts.getTripById(newTrip.getT_id()) != null) {
			return new ResponseEntity<>("Trip exist.", HttpStatus.BAD_REQUEST);
		}
		int newTripNum = ts.addTrip(newTrip);
		return new ResponseEntity<>("Trip with id: "+newTripNum +" has been created.", HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/dlt/{delete}")
	public ResponseEntity<String> deleteUser(@PathVariable("delete") int id){
		if(ts.deleteTrip(id) == false) {
			return new ResponseEntity<>("Trip with "+id+" does not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("User with id "+id+" was deleted",HttpStatus.OK);
	}
}
