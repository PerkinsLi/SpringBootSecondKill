package com.perkins.SpringBootSecondKill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	RedisService redisService;
	
	
	
	


}
