package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.onlineflatrental.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	
	
	
}
