package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.TripController;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.services.TripService;

@WebMvcTest(TripController.class)
public class TripControllerTest {
	
	@MockBean
	private TripService ts;
	@Autowired
	private MockMvc MockMvc;
	
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
	
	public static String convertToJson(Trip trip) {
		try {
			return new ObjectMapper().writeValueAsString(trip);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
	
	@Test
	public void getAllTrips() throws Exception { 
		when(ts.getAllTrips()).thenReturn(mockedTrips);
		
		MockMvc.perform(get("/trip/all").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void getAllTripsFail() throws Exception { 
		when(ts.getAllTrips()).thenReturn(mockedTrips);
		
		MockMvc.perform(get("/trip").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void getTrip() throws Exception { 
		when(ts.getTripById(1)).thenReturn(mockedTrip);
		
		MockMvc.perform(get("/trip/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void getTripFail() throws Exception { 
		when(ts.getTripById(5)).thenThrow(UserDoesNotExistException.class);
		
		MockMvc.perform(get("/trip/5").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void getTripsByUserID() throws Exception { 
		when(ts.getTripByUser(1)).thenReturn(mockedTrips);
		
		MockMvc.perform(get("/trip/user/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void getTripsByUserIDFail() throws Exception { 
		when(ts.getTripByUser(5)).thenThrow(UserDoesNotExistException.class);
		
		MockMvc.perform(get("/trip/user/5").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void createTripValid() throws Exception {
		when(ts.addTrip(new Trip(4,"Pasadenaa","Amarilloa",9,1))).thenReturn(4);
		
		MockMvc.perform(post("/trip")
				.content(convertToJson(new Trip(4,"Pasadenaa","Amarilloa",9,1)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void createTripInvalid() throws Exception {
		when(ts.addTrip(new Trip(4,"Pasadenaa","Amarilloa",9,1))).thenThrow(UserAlreadyExistsException.class);
		
		MockMvc.perform(post("/trip")
				.content(convertToJson(new Trip(4,"Pasadenaa","Amarilloa",9,1)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void deleteTrip() throws Exception { 
		when(ts.deleteTrip(1)).thenReturn(true);
		
		MockMvc.perform(get("/trip/dlt/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void deleteTrip2() throws Exception { 
		when(ts.deleteTrip(0)).thenReturn(true);
		
		MockMvc.perform(get("/trip/dlt/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
}
