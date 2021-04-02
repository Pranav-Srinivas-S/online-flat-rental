package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Landlord;

@Repository
public interface ILandlordRepository extends JpaRepository<Landlord, Integer> {

	
	
}
