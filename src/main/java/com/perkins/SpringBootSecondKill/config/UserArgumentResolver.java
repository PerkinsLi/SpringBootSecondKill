package com.perkins.SpringBootSecondKill.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.service.UserService;
import com.perkins.SpringBootSecondKill.service.impl.UserServiceImpl;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

	@Autowired
	UserService userService;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> cls = parameter.getParameterType();
		return cls == User.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response  = webRequest.getNativeResponse(HttpServletResponse.class);
		String paramToken = request.getParameter(UserServiceImpl.COOKIE_NAME_TOKEN);
		String cookieToken = getCookieToken(request, UserServiceImpl.COOKIE_NAME_TOKEN);
		
		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		
		String token = StringUtils.isEmpty(paramToken)? cookieToken:paramToken;
		User user = userService.getUserByToken(response,token);
		return user;
	}

	private String getCookieToken(HttpServletRequest request, String cookieNameToken) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null || cookies.length <= 0) {  // 为了方便压测
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieNameToken)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
