package com.capg.onlineflatrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Flat;

@Repository
public interface IFlatRepository extends JpaRepository<Flat, Integer> {
	@Query("select f from Flat f where f.cost=?1 and f.availibilty = ?2")
	List<Flat> findByCostAndAvailability(float cost, String availability);


}
