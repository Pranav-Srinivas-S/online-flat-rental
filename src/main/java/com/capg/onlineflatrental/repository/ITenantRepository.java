package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Tenant;

/*
 * Author : PRNANAV SRINIVAS S
 * Version : 1.0
 * Date : 03-04-2921
 * Description : This is Tenant Rpository
*/

@Repository
public interface ITenantRepository extends JpaRepository<Tenant, Integer> {
	

	
}
