package com.lexian.manager.order.bean;

public class User {

	private int id;//自增长
	private String phone;//手机号，同时也是登陆账号
	private String userName;//用户姓名
	private String sex;//性别
	private String mail;//电子邮件地址
	private String portrait;//用户头像URL
	private String passwd;//密码（md5散列）
	private String lastLoginTime;//最近登陆时间
	private int status;//用户状态。1：启用；-1：冻结
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPortrait() {
		return portrait;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
