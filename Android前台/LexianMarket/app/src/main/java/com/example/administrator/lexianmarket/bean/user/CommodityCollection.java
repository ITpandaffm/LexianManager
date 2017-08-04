/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.bean.user;

import com.example.administrator.lexianmarket.bean.commodity.Commodity;
import com.example.administrator.lexianmarket.bean.commodity.CommodityStore;
import com.example.administrator.lexianmarket.bean.commodity.Store;

import java.util.Date;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class CommodityCollection {
	private Integer id;
	private String userId;
	private String commodityNo;
	private String storeNo;
	private long collectTime;
	
	
	private Store store;
	private Commodity commodity;
	private CommodityStore commodityStore;
	
	
	
	public CommodityStore getCommodityStore() {
		return commodityStore;
	}
	public void setCommodityStore(CommodityStore commodityStore) {
		this.commodityStore = commodityStore;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public long getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}
	
	
}
