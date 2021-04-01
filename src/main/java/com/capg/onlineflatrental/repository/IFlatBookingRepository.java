package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.onlineflatrental.entities.FlatBooking;

public interface IFlatBookingRepository extends JpaRepository<FlatBooking, Integer> {
	
	
	
}
