package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.daos.TripRepository;
import com.revature.daos.UserRepository;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.models.UserRole;

public class TripService {
	private TripRepository tr;

	@Autowired
	public TripService(TripRepository tr) {
		super();
		this.tr = tr;
	}
	
	@Transactional(readOnly=true)
	public Trip getTripById(int id) {
//		return ur.getById(id);
		return tr.findById(id).get();
	}
	
	@Transactional
	public List<Trip> getTripByUser(int id) {
		return tr.findAllByUserId(id);
	}
	
	@Transactional
	public List<Trip> getAllTrips() {
		return tr.findAll();
	}
	
	@Transactional
	public int addTrip(Trip t) {
		t.setTimeSubmited(LocalDateTime.now());
		t.setCurrLocation("New York");
		t.setDestination("Harford");
		t.setTimeElapsed(360);
		tr.save(t);
		return t.getT_id();
	}
	
	
	@Transactional
	public boolean deleteTrip(int id) {
		if (getTripById(id) != null) {
			tr.deleteById(id);
			return true;
		}
		return false;
	}
}
