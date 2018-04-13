package com.perkins.SpringBootSecondKill.vo;

import java.util.Date;

import com.perkins.SpringBootSecondKill.domain.Goods;

public class SecondKillGoodsVo extends Goods{

	private Double secondkillGoodsPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	
	public Double getSecondkillGoodsPrice() {
		return secondkillGoodsPrice;
	}
	public void setSecondkillGoodsPrice(Double secondkillGoodsPrice) {
		this.secondkillGoodsPrice = secondkillGoodsPrice;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
