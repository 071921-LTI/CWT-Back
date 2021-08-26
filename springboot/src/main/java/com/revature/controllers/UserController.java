package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService us;
	
	@Autowired
	public UserController(UserService us) {
		super();
		this.us = us;
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id){
		return new ResponseEntity<User>(us.getUserById(id), HttpStatus.OK);
	}
	
	@GetMapping(value="/un/{usrnm}")
	public ResponseEntity<User> getByUsrnm(@PathVariable("usrnm") String usrnm){
		return new ResponseEntity<User>(us.getUserByUsername(usrnm), HttpStatus.OK);
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(us.getUsers(),HttpStatus.ALREADY_REPORTED);
	}
	
	@PostMapping
	public ResponseEntity<String> createUser(@Valid @RequestBody User user){
		if(us.getUserByUsername(user.getUsername())!= null) {
			return new ResponseEntity<>("Username is use.", HttpStatus.BAD_REQUEST);
		}
		
		int newUserId = us.addUser(user);
		return new ResponseEntity<>("User "+ user.getUsername() + " with id of " + newUserId + " has been created.", HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/dlt/{delete}")
	public ResponseEntity<String> deleteUser(@PathVariable("delete") int id){
		if(us.deleteUser(id) == false) {
			return new ResponseEntity<>("Id: "+id+" does not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("User with id "+id+" was deleted",HttpStatus.OK);
	}
}
