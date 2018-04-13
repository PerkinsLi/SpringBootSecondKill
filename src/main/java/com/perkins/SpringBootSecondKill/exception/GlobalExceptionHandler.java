package com.perkins.SpringBootSecondKill.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.result.Result;

/**
 * 全局异常处理，对异常信息进行格式化
 * @author Administrator
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		if (e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			
			return Result.error(ex.getCodeMsg());
		} else if (e instanceof BindException) {
			BindException ex = (BindException)e;
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		} else {
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
}
