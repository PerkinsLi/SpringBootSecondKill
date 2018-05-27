package com.perkins.SpringBootSecondKill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/get")
	public JSONObject getUser(User user) {
		
		return (JSONObject) JSONObject.toJSON(user);
	}
	
	
	


}
