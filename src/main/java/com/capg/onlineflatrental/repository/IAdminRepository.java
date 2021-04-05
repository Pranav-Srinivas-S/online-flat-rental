package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
	

	
}
