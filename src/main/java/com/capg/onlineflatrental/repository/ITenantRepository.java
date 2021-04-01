package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.onlineflatrental.entities.Tenant;

public interface ITenantRepository extends JpaRepository<Tenant, Integer> {
	

	
}
