package com.capg.onlineflatrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.onlineflatrental.entities.Login;
import com.capg.onlineflatrental.entities.User;

@Repository
public interface ILoginRepository extends JpaRepository<Login, User> {


}