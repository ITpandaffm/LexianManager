package com.lexian.manager.goods.bean;

import java.util.Date;
import java.util.List;

public class Commodity {

	private Integer id;
	private String commodityNo;
	private String name;
	private Integer categoryId;
	private String introduce;
	private String detailed;
	private String pictureUrl;
	private Date createTime;
	private Date updateTime;
	private Integer states;
	private CategoryView categoryView;
	private List<String> commodityPicuture;
	
	private List<CommoditySpec> commodtySpecs;
	
	public List<CommoditySpec> getCommodtySpecs() {
		return commodtySpecs;
	}
	public void setCommodtySpecs(List<CommoditySpec> commodtySpecs) {
		this.commodtySpecs = commodtySpecs;
	}
	public List<String> getCommodityPicuture() {
		return commodityPicuture;
	}
	public void setCommodityPicuture(List<String> commodityPicuture) {
		this.commodityPicuture = commodityPicuture;
	}
	public CategoryView getCategoryView() {
		return categoryView;
	}
	public void setCategoryView(CategoryView categoryView) {
		this.categoryView = categoryView;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	
}
