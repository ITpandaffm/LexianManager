package com.lexian.manager.goods.bean;

public class CommodityStore {

	private int id;
	private String commodityNo;
	private String storeNo;
	private double commodotyPrice;
	private double realPrice;
	private int commodityAmont;
	private int commodityLockAmont;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommmodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
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
	public int getCommodityAmont() {
		return commodityAmont;
	}
	public void setCommodityAmont(int commodityAmont) {
		this.commodityAmont = commodityAmont;
	}
	public int getCommodityLockAmont() {
		return commodityLockAmont;
	}
	public void setCommodityLockAmont(int commodityLockAmont) {
		this.commodityLockAmont = commodityLockAmont;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
