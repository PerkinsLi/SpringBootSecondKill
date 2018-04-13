package com.perkins.SpringBootSecondKill.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	public static final String salt = "1a2b3c4d";
	
	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	// 将输入密码进行MD5加密
	public static String inputPassFormPass(String inputPass) {
		String str = salt.charAt(0) + salt.charAt(3) +inputPass + salt.charAt(5);
		return md5(str);
	}
	
	// 将经过一次MD5加密的字符串在进行一次加密，作为存入数据库的字符串
	public static String formPassToDBPass(String inputPass, String salt) {
		String str = salt.charAt(0) + salt.charAt(3) +inputPass + salt.charAt(5);
		return md5(str);
	}
	
	public static String inputPassToDBPass(String inputPass, String salt) {
		String formPass = inputPassFormPass(inputPass);
		String DBPass = formPassToDBPass(formPass, salt);
		return DBPass;
	}
	
	
}
