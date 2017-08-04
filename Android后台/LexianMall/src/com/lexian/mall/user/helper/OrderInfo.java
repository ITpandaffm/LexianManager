/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.helper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class OrderInfo {
	
	private String paymentType="在线支付";
	
	private String paymentSubtype="余额支付";
	
	private String deliveryType="上门自提";
	
	private String userId;
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentSubtype() {
		return paymentSubtype;
	}

	public void setPaymentSubtype(String paymentSubtype) {
		this.paymentSubtype = paymentSubtype;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Override
	public String toString() {
		return "OrderInfo [paymentType=" + paymentType + ", paymentSubtype=" + paymentSubtype + ", deliveryType="
				+ deliveryType + ", userId=" + userId + "]";
	}
	
	
	
	
}
