package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.onlineflatrental.entities.Landlord;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 03-04-2921
 * Description : This is Landlord Repository
*/

@Repository
public interface ILandlordRepository extends JpaRepository<Landlord, Integer> {


	
}
