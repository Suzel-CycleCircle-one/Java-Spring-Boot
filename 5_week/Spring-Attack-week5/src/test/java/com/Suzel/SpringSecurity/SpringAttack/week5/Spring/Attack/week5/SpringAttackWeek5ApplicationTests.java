package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAttackWeek5ApplicationTests {

	@Autowired
   private JwtService jwtService;

	@Test
	void contextLoads() {

//		UserEntity user = new UserEntity(4L, "Suzel@Gmail.com", "12345");
//
//		String token =  jwtService.generateToken(user);
//
//		System.out.println(token);
//
//		Long id = jwtService.getUserIdFromToken(token);
//
//		System.out.println(id);
	}

}
