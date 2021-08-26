package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.daos.TripRepository;
import com.revature.models.Trip;
import com.revature.services.TripService;

public class ServicesTest {
	private TripRepository tr;
	TripService testService = new TripService(tr);
	Trip newTrip = new Trip(); // TODO pass correct parameters
	
	@Test
	@Order(1)
	public void addTrip_Pass() {
		assertEquals(1,testService.addTrip(newTrip));
	}
	
	@Test
	@Order(2)
	public void addTrip_Fail() {
		assertEquals(0,testService.addTrip(newTrip));
	}
	
	@Test
	@Order(3)
	public void getTripById_Pass() {
		assertEquals(1,testService.getTripById(1).getT_id());
	}
	
	@Test
	@Order(4)
	public void getTripById_Fail() {
		assertEquals(0,testService.getTripById(1).getT_id());
	}
	
	@Test
	@Order(5)
	public void deleteTrip_Pass() {
		assertEquals(true,testService.deleteTrip(1));
	}
	
	//this test depends on deleteTrip_Pass() passing
	@Test
	@Order(6)
	public void deleteTrip_Fail() {
		assertEquals(true,testService.deleteTrip(0)); 
	}
	
}