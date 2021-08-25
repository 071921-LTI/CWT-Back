package com.revature.daos;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findUserById(int id);
	
	@Query("Select u from User u Where u.username = ?1")
	User findUserByUsername(String username);
	
	
//	@Query("Select u from User u")
//	List<User> getUsers();
	
	
}
