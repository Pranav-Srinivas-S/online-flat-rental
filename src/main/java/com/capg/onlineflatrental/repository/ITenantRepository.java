package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Tenant;

@Repository
public interface ITenantRepository extends JpaRepository<Tenant, Integer> {
	

	
}
