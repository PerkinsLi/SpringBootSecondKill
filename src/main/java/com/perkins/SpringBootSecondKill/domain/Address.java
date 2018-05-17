package com.perkins.SpringBootSecondKill.domain;

public class Address {

	private long id;
	private long userId;
	private String consigneeName;
	private String mobile;
	private String address;
	
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
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", consigneeName=" + consigneeName + ", mobile=" + mobile
				+ ", address=" + address + "]";
	}
	
	
}
