package com.lexian.manager.goods.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Browse {

	private int id;
	private String commodityNo;
	private String userId;
	private Date browseTime;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBrowseTime() {
		return browseTime;
	}
	public void setBrowseTime(Date browseTime) {
		this.browseTime = browseTime;
	}

}
