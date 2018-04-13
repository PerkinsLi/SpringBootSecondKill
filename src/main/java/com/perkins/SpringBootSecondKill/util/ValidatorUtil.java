package com.perkins.SpringBootSecondKill.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidatorUtil {

	// 电话号码格式
	public static final Pattern mobile_patter = Pattern.compile("1\\d{10}");
	
	// 验证输入的电话号码是否符合格式
	public static boolean isMobile(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		
		Matcher matcher = mobile_patter.matcher(str);
		return matcher.matches();
	}
	
}
