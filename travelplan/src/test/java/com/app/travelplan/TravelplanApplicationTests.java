package com.app.travelplan;

import com.app.travelplan.model.Sessions;
import com.app.travelplan.service.PlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TravelplanApplicationTests {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		System.out.println(passwordEncoder.encode("admin"));
		System.out.println(passwordEncoder.encode("123456"));
		System.out.println(passwordEncoder.encode("123456"));
	}
}
