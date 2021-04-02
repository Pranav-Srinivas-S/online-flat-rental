package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Flat;

@Repository
public interface IFlatRepository extends JpaRepository<Flat, Integer> {


}
