package com.perkins.SpringBootSecondKill.rabbitmq;

import com.perkins.SpringBootSecondKill.domain.User;

public class SKMessage {

	private User skUser;
	private long goodsId;
	
	
	public User getSkUser() {
		return skUser;
	}
	public void setSkUser(User skUser) {
		this.skUser = skUser;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	
}
