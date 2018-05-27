package com.perkins.SpringBootSecondKill.redis;

public class SecondKillKey extends BasePrefix{

	public SecondKillKey( String prefix) {
		super( prefix);
	}

	public static SecondKillKey goodsOver = new SecondKillKey("GoodsOver");
}
