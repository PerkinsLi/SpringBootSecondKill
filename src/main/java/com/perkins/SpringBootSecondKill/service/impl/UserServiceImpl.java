package com.perkins.SpringBootSecondKill.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perkins.SpringBootSecondKill.dao.UserDao;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.exception.GlobalException;
import com.perkins.SpringBootSecondKill.redis.RedisService;
import com.perkins.SpringBootSecondKill.redis.UserKey;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.service.UserService;
import com.perkins.SpringBootSecondKill.util.MD5Util;
import com.perkins.SpringBootSecondKill.util.UUIDUtil;
import com.perkins.SpringBootSecondKill.vo.LoginVo;

/**
 * 提供用户服务
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{

	public static final String COOKIE_NAME_TOKEN = "token";
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RedisService redisService;
	
	/**
	 * 通过id获取用户信息
	 */
	public User getUserById(Long id) {
		return userDao.getById(id);
	}

	/**
	 * 用户登录
	 */
	public String login(HttpServletResponse response, LoginVo loginVo) {

		if (loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();
		User user = userDao.getById(Long.parseLong(mobile));
		
		if (user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		
		// 验证密码
		String DBPass = user.getPassword();
		String salt = user.getSalt();
		String realPass = MD5Util.inputPassToDBPass(formPass, salt); // 测试需要
		 
		if (!realPass.equals(DBPass)) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		
		// 生成cookie
		String token = UUIDUtil.uuid();
		addCookie(user, token, response);
		
		return token;
	}

	/**
	 * 根据cookie token 从redis中取用户信息
	 * @param token
	 * @return
	 */
	public User getUserByToken(HttpServletResponse response, String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		
		User user = redisService.get(UserKey.token, token, User.class);
		
		if (user != null) {
			
			addCookie(user,token, response);
		}
		return user;
	}
	
	/**
	 * 添加cookie
	 * @param user
	 * @param response
	 */
	private void addCookie(User user, String token, HttpServletResponse response) {
		redisService.set(UserKey.token, token, user);
		Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
		cookie.setMaxAge(UserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
		
		
	}
}
