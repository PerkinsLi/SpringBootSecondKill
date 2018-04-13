package com.perkins.SpringBootSecondKill.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.UserService;
import com.perkins.SpringBootSecondKill.vo.LoginVo;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	/**
	 * 访问登录页面
	 * @return
	 */
	@RequestMapping("/to_login")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * 登录
	 * @param loginVo
	 * @return
	 */
	@PostMapping("/do_login")
	@ResponseBody
	public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
		String token = userService.login(response, loginVo);
		return Result.success(token);
	}
}
