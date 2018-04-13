package com.perkins.SpringBootSecondKill.service;

import javax.servlet.http.HttpServletResponse;

import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.vo.LoginVo;

public interface UserService {

	User getUserById(Long id);
	
	String login(HttpServletResponse response, LoginVo loginVo);
	
	User getUserByToken(HttpServletResponse response, String token);
}
