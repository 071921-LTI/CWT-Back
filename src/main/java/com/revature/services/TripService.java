package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.daos.TripRepository;
import com.revature.models.Trip;

@Service
public class TripService {
	private TripRepository tr;

	@Autowired
	public TripService(TripRepository tr) {
		super();
		this.tr = tr;
	}
	
	public Trip getTripById(int id) {
		return tr.findById(id).get();
	}
	
	public List<Trip> getTripByUser(int id) {
		return tr.findAllByUserId(id);
	}
	
	public List<Trip> getAllTrips() {
		return tr.findAll();
	}
	
	public Trip getTripByLocationAndDestination(String location,String destination) {
		return tr.findByLocationDestination(location,destination);
	}
	
	
	@Transactional
	public int addTrip(Trip t) {
		t.setSubmitted_datetime(LocalDateTime.now());
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
