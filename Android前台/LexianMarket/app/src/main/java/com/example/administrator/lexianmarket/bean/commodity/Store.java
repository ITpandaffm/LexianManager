/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.bean.commodity;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class Store  implements Serializable {

	private Integer id;
	private String storeNo;
	private Integer provinceId;
	private Integer cityId;
	private Integer countyId;
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
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}

