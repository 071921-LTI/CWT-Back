package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.security.Timestamp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.daos.TripDao;
import com.revature.daos.TripHibernate;
import com.revature.daos.UserDao;
import com.revature.daos.UserHibernate;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.models.UserRole;


public class HibernateTest {
	TripDao testTrip = new TripHibernate();
	UserDao testUser = new UserHibernate();
	Timestamp today = new Timestamp(null, null);
	Trip newTrip = new Trip(); // TODO pass correct parameters
	UserRole testRole;
	User newUser = new User(1,"test","pass",testRole);
	
	//Trip hibernate test
	@Test
	@Order(1)
	public void getTripById_Pass() {
		assertEquals(1,testTrip.getTripById(1).getT_id());
	}
	
	@Test
	@Order(2)
	public void getTripById_Fail(){
		assertEquals(0, testTrip.getTripById(1).getT_id());
	}
	
	@Test
	@Order(3)
	public void addTrip_Pass() {
		assertEquals(1, testTrip.addTrip(newTrip));
	}
	
	@Test
	@Order(4)
	public void addTrip_Fail(){
		assertEquals(0, testTrip.addTrip(newTrip));
	}
	
	@Test
	@Order(5)
	public void deleteTrip_Pass() {
		assertEquals(true, testTrip.deleteTrip(null));
	}
	
	@Test
	@Order(6)
	public void deleteTrip_Fail() {
		assertEquals(false, testTrip.deleteTrip(null));
	}
	
	//User hibernate test
	
	@Test
	@Order(7)
	public void addUser_Pass() {
		assertEquals(1, testUser.addUser(newUser));
	}
	
	@Test
	@Order(8)
	public void addUser_Fail() {
		assertEquals(2, testUser.addUser(newUser));
	}
	
	@Test
	@Order(9)
	public void getUserById_Pass() {
		assertEquals(1, testUser.getUserById(1).getId());
	}
	
	@Test
	@Order(10)
	public void getUserById_Fail() {
		assertEquals(0, testUser.getUserById(1).getId());
	}
	
	@Test
	@Order(11)
	public void getUserByUsername_Pass() {
		assertEquals("test", testUser.getUserByUsername("test").getUsername());
	}
	
	@Test
	@Order(12)
	public void getUserByUsername_Fail() {
		assertEquals("notTest", testUser.getUserByUsername("test").getUsername());
	}
	
	@Test
	@Order(13)
	public void deleteUser_Pass() {
		assertEquals(true, testUser.deleteUser(newUser));
	}
	
	@Test
	@Order(14)
	public void deleteUser_Fail() {
		assertEquals(false, testUser.deleteUser(newUser));
	}
}
