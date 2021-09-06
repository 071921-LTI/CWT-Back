package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.controllers.UserController;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.services.TripService;
import com.revature.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@MockBean
	private UserService us;
	@Autowired
	private MockMvc MockMvc;
	
	static User mockUser;
	static List<User> mockUsers;
	
	@BeforeAll
	public static void initial() {
		User user1 = new User("user1","pass");
		User user2 = new User("user2","pass2");
		User user3 = new User("user3","pass3");
		mockUsers = new ArrayList<>();
		mockUsers.add(user1);
		mockUsers.add(user2);
		mockUsers.add(user3);
		
		mockUser = user2;
	}
	
	public static String convertToJson(User user) {
		try {
			return new ObjectMapper().writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
	@Test
	public void getUsers() throws Exception { 
		when(us.getUsers()).thenReturn(mockUsers);
		
		MockMvc.perform(get("/users/all").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void getUsersFail() throws Exception { 
		when(us.getUsers()).thenReturn(mockUsers);
		
		MockMvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void getUser() throws Exception { 
		when(us.getUserById(1)).thenReturn(mockUser);
		
		MockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void getUserFail() throws Exception { 
		when(us.getUserById(5)).thenThrow(UserDoesNotExistException.class);
		
		MockMvc.perform(get("/users/5").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void getUserByName() throws Exception { 
		when(us.getUserByUsername("user1")).thenReturn(mockUser);
		
		MockMvc.perform(get("/users/un/user1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getUserByNameFail() throws Exception { 
		when(us.getUserByUsername("user5")).thenThrow(UserDoesNotExistException.class);
		
		MockMvc.perform(get("/users/un/user5").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void createUserValid() throws Exception {
		when(us.addUser(new User("testUser","pass"))).thenReturn(4);
		
		MockMvc.perform(post("/users")
				.content(convertToJson(new User("testUser","pass")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void createUserInvalid() throws Exception {
		when(us.addUser(new User("testUser","pass"))).thenThrow(UserAlreadyExistsException.class);
		
		MockMvc.perform(post("/users")
				.content(convertToJson(new User("testUser","pass")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void deleteUser() throws Exception { 
		when(us.deleteUser(1)).thenReturn(true);
		
		MockMvc.perform(get("/users/dlt/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void deleteUser2() throws Exception { 
		when(us.deleteUser(0)).thenReturn(true);
		
		MockMvc.perform(get("/users/dlt/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
}