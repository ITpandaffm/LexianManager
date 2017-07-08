package com.lexian.manager.goods.bean;

import java.sql.Time;
import java.util.Date;

public class Store {

	private int id;
	private String storeNo;
	private int provinceId;
	private int cityId;
	private int countyId;
	private String storeName;
	private String storeAddress;
	private double maxLongItude;
	private double minLongItude;
	private double maxLatItude;
	private double minLatItude;
	private double longItude;
	private double latItude;
	private String introduce;
	private Date startTime;
	private Date closeTime;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getCountyId() {
		return countyId;
	}
	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public double getMaxLongItude() {
		return maxLongItude;
	}
	public void setMaxLongItude(double maxLongItude) {
		this.maxLongItude = maxLongItude;
	}
	public double getMinLongItude() {
		return minLongItude;
	}
	public void setMinLongItude(double minLongItude) {
		this.minLongItude = minLongItude;
	}
	public double getMaxLatItude() {
		return maxLatItude;
	}
	public void setMaxLatItude(double maxLatItude) {
		this.maxLatItude = maxLatItude;
	}
	public double getMinLatItude() {
		return minLatItude;
	}
	public void setMinLatItude(double minLatItude) {
		this.minLatItude = minLatItude;
	}
	public double getLongItude() {
		return longItude;
	}
	public void setLongItude(double longItude) {
		this.longItude = longItude;
	}
	public double getLatItude() {
		return latItude;
	}
	public void setLatItude(double latItude) {
		this.latItude = latItude;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
