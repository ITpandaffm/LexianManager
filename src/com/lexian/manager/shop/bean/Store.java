/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class Store {

	private Integer id;
	@Pattern(regexp="^\\d{4}$")
	private String storeNo;
	
	private Integer provinceId;
	private Integer cityId;
	private Integer countyId;
	
	@Pattern(regexp="^[0-9a-zA-Z\u4e00-\u9fa5]{2,30}$")
	private String storeName;
	@Size(min=2,max=100)
	private String storeAddress;
	@Range(max=180,min=-180)
	private Double maxLongItude;
	@Range(max=180,min=-180)
	private Double minLongItude;
	@Range(max=90,min=-90)
	private Double maxLatItude;
	@Range(max=90,min=-90)
	private Double minLatItude;
	@Range(max=180,min=-180)
	private Double longItude;
	@Range(max=90,min=-90)
	private Double latItude;
	@Size(min=2,max=100)
	private String introduce;
	@DateTimeFormat(pattern="HH:mm")
	private Date startTime;
	@DateTimeFormat(pattern="HH:mm")
	private Date closeTime;
	@Range(min=1,max=2)
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
	public Double getMaxLongItude() {
		return maxLongItude;
	}
	public void setMaxLongItude(Double maxLongItude) {
		this.maxLongItude = maxLongItude;
	}
	public Double getMinLongItude() {
		return minLongItude;
	}
	public void setMinLongItude(Double minLongItude) {
		this.minLongItude = minLongItude;
	}
	public Double getMaxLatItude() {
		return maxLatItude;
	}
	public void setMaxLatItude(Double maxLatItude) {
		this.maxLatItude = maxLatItude;
	}
	public Double getMinLatItude() {
		return minLatItude;
	}
	public void setMinLatItude(Double minLatItude) {
		this.minLatItude = minLatItude;
	}
	public Double getLongItude() {
		return longItude;
	}
	public void setLongItude(Double longItude) {
		this.longItude = longItude;
	}
	public Double getLatItude() {
		return latItude;
	}
	public void setLatItude(Double latItude) {
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
