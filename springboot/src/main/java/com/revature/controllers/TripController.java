package com.revature.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping
	public ResponseEntity<String> createTrip(@Valid @RequestBody Trip newTrip){
		System.out.println(newTrip); 
		if(ts.getTripByName(newTrip.getTripName()) != null) {
			return new ResponseEntity<>("Trip exist.", HttpStatus.BAD_REQUEST);
		}
		int newTripNum = ts.addTrip(newTrip);
		return new ResponseEntity<>("Trip id: "+newTripNum +" "+newTrip.getTripName() + "trip has been created.", HttpStatus.CREATED);
	}
}
