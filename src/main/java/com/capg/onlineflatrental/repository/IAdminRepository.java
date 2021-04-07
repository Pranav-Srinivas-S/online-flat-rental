package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Admin;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Admin Repository
*/

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
	

	
}
