package com.capg.onlineflatrental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.onlineflatrental.service.FlatServiceImpl;

@SpringBootTest
class OnlineFlatRentalApplicationTests {
	@Autowired
	FlatServiceImpl testService;

	@Test
	void contextLoads() {
	}

}
