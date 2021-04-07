package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.User;

/*
 * Author : RAVURU SATHYA NAGA SIVANANDANA SAI BHARATH
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is User Repository
*/

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String username);
	
	@Query("select u from User u where u.userId = ?1 and u.userName = ?2")
	User findByIdAndName(int id, String userName);
	
}
