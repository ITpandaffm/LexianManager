package com.lexian.manager.shop.bean;

public class CommodityStore {

	private Integer id;
	private String commmodityNo;
	private String storeNo;
	private double commodotyPrice;
	private double realPrice;
	private Integer commodityAmont;
	private Integer commodityLockAmont;
	private Integer type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommmodityNo() {
		return commmodityNo;
	}
	public void setCommmodityNo(String commmodityNo) {
		this.commmodityNo = commmodityNo;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public double getCommodotyPrice() {
		return commodotyPrice;
	}
	public void setCommodotyPrice(double commodotyPrice) {
		this.commodotyPrice = commodotyPrice;
	}
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getCommodityAmont() {
		return commodityAmont;
	}
	public void setCommodityAmont(Integer commodityAmont) {
		this.commodityAmont = commodityAmont;
	}
	public Integer getCommodityLockAmont() {
		return commodityLockAmont;
	}
	public void setCommodityLockAmont(Integer commodityLockAmont) {
		this.commodityLockAmont = commodityLockAmont;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
