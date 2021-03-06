package com.capg.onlineflatrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Flat;

/*
 * Author : AJITHKUMAR A 
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Flat Repository
*/

@Repository
public interface IFlatRepository extends JpaRepository<Flat, Integer> {
	
	@Query("select f from Flat f where f.flatCost = ?1 and f.flatAvailability = ?2")
	List<Flat> findByCostAndAvailability(float flatCost, String availability);

}
