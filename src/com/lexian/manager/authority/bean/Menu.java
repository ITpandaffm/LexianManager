package com.lexian.manager.authority.bean;

public class Menu {

	private Integer id;
	private String url;
	private String name;
	private String backUrl;
	private Integer parentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", url=" + url + ", name=" + name + ", backUrl=" + backUrl + ", parentId=" + parentId
				+ "]";
	}
	
	
}
