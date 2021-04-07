package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String username);
	
	@Query("select u from User u where u.userId = ?1 and u.userName = ?2")
	User findByIdAndName(int id, String userName);	
}
