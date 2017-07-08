package com.lexian.manager.order.bean;

public class Orders {
	private int id;
	private String orderNo;//订单编号
	private String userId;//订单所属用户id 引用 ：user.id
	private String storeNo;//引用：store.store_no; 订单取货门店
	private double totalAmount;//订单总金额
	private String paymentType;//支付类型
	private String paymentSubty;//支付子类型
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentSubty() {
		return paymentSubty;
	}
	public void setPaymentSubty(String paymentSubty) {
		this.paymentSubty = paymentSubty;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	private String deliveryType;//配送类型
	private int states;//订单状态：1：待付款 2：待发货  3：已发货  4：已结单
	
}
