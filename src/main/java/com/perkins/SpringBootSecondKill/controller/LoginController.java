package com.perkins.SpringBootSecondKill.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.redis.UserKey;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.UserService;
import com.perkins.SpringBootSecondKill.service.impl.UserServiceImpl;
import com.perkins.SpringBootSecondKill.vo.LoginVo;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	/**
	 * 登录
	 * @param loginVo
	 * @return
	 */
	@RequestMapping("/do_login")
	public JSONObject doLogin(HttpServletResponse response,  @Valid LoginVo loginVo) {
		String token = userService.login(response, loginVo);
		JSONObject succes = (JSONObject) JSONObject.toJSON(Result.success(token));
		return succes;
	}
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("/logoff")
	public JSONObject logoff(HttpServletRequest request) {
		String cookieValue = "";
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null || cookies.length <= 0) {  // 为了方便压测
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(UserServiceImpl.COOKIE_NAME_TOKEN)) {
				cookieValue = cookie.getValue();
			}
		}
		/**
		 * 将用户信息从redis删除
		 */
	    Boolean bl = redisService.delete(UserKey.token, cookieValue);
	    return (JSONObject) JSONObject.toJSON(Result.success(bl));
	}
}
