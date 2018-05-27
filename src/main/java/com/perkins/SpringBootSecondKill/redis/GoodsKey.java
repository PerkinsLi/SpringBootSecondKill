package com.perkins.SpringBootSecondKill.redis;

public class GoodsKey extends BasePrefix{

	public GoodsKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	
	public static GoodsKey secondKillGoodsStock= new GoodsKey(0, "gs");

}
