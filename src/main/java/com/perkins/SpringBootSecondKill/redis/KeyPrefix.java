package com.perkins.SpringBootSecondKill.redis;

public interface KeyPrefix {

	public int expireSeconds();
	
	public String getPrefix();
}
