package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.onlineflatrental.entities.Flat;

public interface IFlatRepository extends JpaRepository<Flat, Integer> {


}
