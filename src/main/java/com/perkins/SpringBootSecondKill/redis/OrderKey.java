package com.perkins.SpringBootSecondKill.redis;

public class OrderKey extends BasePrefix{
	public OrderKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	
	public static KeyPrefix getSKOrderByUidGid = new OrderKey(0, "moug");

}
