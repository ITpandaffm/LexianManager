/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.bean.commodity;

import java.io.Serializable;
import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class CommodityStore  implements Serializable {

	private int id;
	private String commodityNo;
	private String storeNo;
	private double commodityPrice;
	private double realPrice;
	private int commodityAmont;
	private int commodityLockAmont;
	private int type;
	
	private Store store;
	private Commodity commodity;
	private List<CommodityPicture> commodityPicutures;
	
	
	public List<CommodityPicture> getCommodityPicutures() {
		return commodityPicutures;
	}
	public void setCommodityPicutures(List<CommodityPicture> commodityPicutures) {
		this.commodityPicutures = commodityPicutures;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(double commodityPrice) {
		this.commodityPrice = commodityPrice;
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
