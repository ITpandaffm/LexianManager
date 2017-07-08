package com.lexian.manager.goods.bean;

public class CommoditySpec {

	private int id;
	private String commodityNo;
	private String specGroup;
	private String specName;
	private int states;
	public String getSpecGroup() {
		return specGroup;
	}
	public void setSpecGroup(String specGroup) {
		this.specGroup = specGroup;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
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
}
