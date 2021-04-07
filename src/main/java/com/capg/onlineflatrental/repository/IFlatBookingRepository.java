package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.FlatBooking;

/*
 * Author : SHAIK ABDUL BASHEER 
 * Version : 1.0
 * Date : 03-04-2921
 * Description : This is Flat Booking Repository
*/

@Repository
public interface IFlatBookingRepository extends JpaRepository<FlatBooking, Integer> {
	
	
	
}
