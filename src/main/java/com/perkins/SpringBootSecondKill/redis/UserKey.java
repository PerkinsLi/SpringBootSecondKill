package com.perkins.SpringBootSecondKill.redis;

public class UserKey extends BasePrefix{

	public static final int DEFAULT_TOKEN_EXPIRE_TIME = 3600*24*2;
	private UserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static UserKey token = new UserKey(DEFAULT_TOKEN_EXPIRE_TIME, "token");

}
