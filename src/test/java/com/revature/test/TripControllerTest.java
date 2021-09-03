package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.controllers.TripController;
import com.revature.models.Trip;
import com.revature.services.TripService;

@WebMvcTest(TripController.class)
public class TripControllerTest {
	
	@MockBean
	private TripService tripsv;
	@Autowired
	private MockMvc tripmock;
	
	static Trip mockedTrip;
	static List<Trip> mockedTrips;
	
	@BeforeAll
	public static void initial() {
		Trip trip1 = new Trip(1,"Houston","Austin",3,1);
		Trip trip2 = new Trip(2,"San Antonio","Dallas",4,1);
		Trip trip3 = new Trip(3,"Pasadena","Amarillo",9,1);
		mockedTrips = new ArrayList<>();
		mockedTrips.add(trip1);
		mockedTrips.add(trip2);
		mockedTrips.add(trip3);
		mockedTrip = trip1;
	}
	
	@Test
	public void getAllTripsBasicUser() throws Exception { 
		when(tripsv.getAllTrips()).thenReturn(mockedTrips);
		
		tripmock.perform(get("/trip/all").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
}
