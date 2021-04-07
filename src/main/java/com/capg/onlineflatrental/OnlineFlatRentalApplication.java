package com.capg.onlineflatrental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class OnlineFlatRentalApplication {

	final static Logger LOGGER = LoggerFactory.getLogger(OnlineFlatRentalApplication.class);
	
	public static void main(String[] args) {
		
		LOGGER.info("Online Flat Rental Application Initiated");
		
		SpringApplication.run(OnlineFlatRentalApplication.class, args);
	}

}