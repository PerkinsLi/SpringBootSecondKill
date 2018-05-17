package com.perkins.SpringBootSecondKill.domain;

import java.util.Date;

public class ShopingCart {
	
	private long id;
	private long  userId;
	private long goodsId;
	private int goodsNumber;
	private Date addDate;
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
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


	@Override
	public String toString() {
		return "ShopingCart [id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", goodsNumber=" + goodsNumber
				+ ", addDate=" + addDate + "]";
	}
	
	
	
}
