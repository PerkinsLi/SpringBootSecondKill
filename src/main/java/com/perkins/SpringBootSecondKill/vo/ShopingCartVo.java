package com.perkins.SpringBootSecondKill.vo;

import java.util.Date;

import com.perkins.SpringBootSecondKill.domain.Goods;

public class ShopingCartVo extends Goods{

	private long scId;
	private long userId;
	private int goodsNumber;
	private Date addDate;
	
	
	public long getScId() {
		return scId;
	}
	public void setScId(long scId) {
		this.scId = scId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	
}
