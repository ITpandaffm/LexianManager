/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.helper;

import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class CashParams {
	private List<CashItem> cashItems;
	public List<CashItem> getCashItems() {
		return cashItems;
	}
	public void setCashItems(List<CashItem> cashItems) {
		this.cashItems = cashItems;
	}
	private OrderInfo orderInfo=new OrderInfo();
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	@Override
	public String toString() {
		return "CashParams [cashItems=" + cashItems + ", orderInfo=" + orderInfo + "," +
				" password=" + password + "]";
	}
	
}
