package com.revature.services;

import java.util.List;

import com.revature.daos.TripDao;
import com.revature.daos.TripHibernate;
import com.revature.models.Trip;

public class TripServiceImpl implements TripService{
	
	TripDao td = new TripHibernate();
	@Override
	public Trip getTripById(int id) {
		// TODO Auto-generated method stub
		return td.getTripById(id);
	}

	@Override
	public List<Trip> getAllTrips() {
		// TODO Auto-generated method stub
		return td.getAllTrips();
	}

	@Override
	public List<Trip> getTripsByUser(int id) {
		// TODO Auto-generated method stub
		return td.getTripsByUser(id);
	}

	@Override
	public int addTrip(Trip t) {
		// TODO Auto-generated method stub
		return td.addTrip(t);
	}

	@Override
	public boolean deleteTrip(Trip t) {
		return td.deleteTrip(t);
		
	}

}
