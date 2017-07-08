package com.lexian.manager.authority.bean;

public class Privilege {
	private int id;//自增长
	private String url;//该权限所对应的服务端URL地址，例如一个html页面地址或者一个服务借口（.do）地址
	private String name;//权限名称
	private String description;//权限信息描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
