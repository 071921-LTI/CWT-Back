package com.revature.daos;

import java.util.List;

import com.revature.models.Trip;

public interface TripDao {
	
	
	Trip getTripById(int id);
	List<Trip> getAllTrips();
	List<Trip> getTripsByUser(int id);
	int addTrip(Trip t);
	boolean deleteTrip(Trip t);
}
