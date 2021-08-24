package com.revature.services;

import java.util.List;

import com.revature.daos.UserDao;
import com.revature.daos.UserHibernate;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public class UserServiceImpl implements UserService {
	
	UserDao ud = new UserHibernate();
	
	@Override
	public User getUserById(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return ud.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return ud.getUserByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return ud.getUsers();
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return ud.addUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return ud.deleteUser(user);
	}

}
