package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.FlatBooking;

@Repository
public interface IFlatBookingRepository extends JpaRepository<FlatBooking, Integer> {
	
	
	
}
