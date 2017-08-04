/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class Commodity {

	private Integer id;
	@Pattern(regexp="^\\d{13}$")
	private String commodityNo;
	@Size(min=1,max=20)
	private String name;
	
	private Integer categoryId;
	@Size(min=1,max=2000)
	private String introduce;
	
	private String detailed;
	
	private String pictureUrl;
	
	@Null
	private Date createTime;
	@Null
	private Date updateTime;
	@Range(max=1,min=-1)
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
