package com.revature.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	
	
	@Query("select t from Trip t where t.user_id =?1")
	List<Trip> findAllByUserId(int id);
}
