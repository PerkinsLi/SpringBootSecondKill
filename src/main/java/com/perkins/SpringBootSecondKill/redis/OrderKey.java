package com.perkins.SpringBootSecondKill.redis;

public class OrderKey extends BasePrefix{

	public OrderKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
		// TODO Auto-generated constructor stub
	}

}