package com.perkins.SpringBootSecondKill.domain;

import java.util.Date;

public class SecondKillGoods {

	private Long id;
	private Long goodsId;
	private Double secondkillGoodsPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
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
	@Override
	public String toString() {
		return "SecondKillGoods [id=" + id + ", goodsId=" + goodsId + ", secondkillGoodsPrice=" + secondkillGoodsPrice
				+ ", stockCount=" + stockCount + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
