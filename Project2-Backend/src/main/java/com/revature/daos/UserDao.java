package com.revature.daos;

import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDao {
	
	User getUserById(int id) throws UserNotFoundException;
	User getUserByUsername(String username) throws UserNotFoundException;
	List<User> getUsers();
	int addUser(User user);
	boolean deleteUser(User user);

}
