package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.revature.controllers.UserController;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.services.TripService;
import com.revature.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@MockBean
	private UserService userSv;
	@Autowired
	private MockMvc userMock;
	
	static User mockedUser;
	static List<User> mockedUsers;
	
	@BeforeAll
	public static void initial() {
		User user1 = new User("user1","pass");
		User user2 = new User("user2","pass2");
		User user3 = new User("user3","pass3");
		mockedUsers = new ArrayList<>();
		mockedUsers.add(user1);
		mockedUsers.add(user2);
		mockedUsers.add(user3);
		mockedUser = user2;
	}
	
	@Test
	public void getAllTripsBasicUser() throws Exception { 
		when(userSv.getUsers()).thenReturn(mockedUsers);
		
		userMock.perform(get("/users/all").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
}